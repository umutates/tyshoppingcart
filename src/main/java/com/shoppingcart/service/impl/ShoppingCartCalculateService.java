package com.shoppingcart.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.entity.Campaign;
import com.shoppingcart.entity.Product;
import com.shoppingcart.entity.ResponseShoppingBill;
import com.shoppingcart.entity.ShoppingCart;
import com.shoppingcart.service.ICampaignService;
import com.shoppingcart.service.IShoppingCartCalculateService;
import com.shoppingcart.service.IShoppingCartService;
import com.shoppingcart.util.DiscountType;

@Service
public class ShoppingCartCalculateService implements IShoppingCartCalculateService {
	
	@Autowired
	ICampaignService campaignService;
	
	@Autowired
	IShoppingCartService shoppingCartService;

	@Override
	public ResponseShoppingBill calculateCartWithinDiscount(ShoppingCart shoppingCart) {
		ResponseShoppingBill responseShoppingBill=new ResponseShoppingBill();
		shoppingCart.getProducts().forEach(product -> product.setDiscountedPrice(product.getPrice()));
		Map<Long,List<Product>> productsByCategory=getProductsByCategory(shoppingCart.getProducts());
		productsByCategory.forEach((k,v)->applyDiscountByGroup(v,k));
		shoppingCart.getProducts().forEach(product->{
			responseShoppingBill.setTotalAmount(responseShoppingBill.getTotalAmount()+product.getPrice());
			responseShoppingBill.setTotalAmountAfterDiscounts(responseShoppingBill.getTotalAmountAfterDiscounts()+product.getDiscountedPrice());
		    responseShoppingBill.setCampaignDiscount(responseShoppingBill.getTotalAmount()-responseShoppingBill.getTotalAmountAfterDiscounts());
		    responseShoppingBill.setTotalAmountAfterDiscounts(applyCoupon(responseShoppingBill.getTotalAmountAfterDiscounts()));
		});
		return null;
  }
 
	private double applyCoupon(double shoppingAmount) {
		return shoppingAmount;
		// TODO Auto-generated method stub
		
	}

	private Map<Long, List<Product>> getProductsByCategory(List<Product> products){
	Map<Long,List<Product>>productsByCategoryMap=new HashMap<>();
	products.stream().map(product -> product.getCategory().getId()).distinct()
		.forEach(categoryID -> {
		 List<Product> productByGroup=products.stream()
					.filter(product -> product.getCategory().getId().longValue() == categoryID.longValue()).collect(Collectors.toList());
		 productsByCategoryMap.put(categoryID, productByGroup);
		});
	  return productsByCategoryMap;
  }
 	
  
  private void applyDiscountByGroup(List<Product> products,Long categoryId) {
	  List<Campaign> campaignList=campaignService.findByCategoryId(categoryId);
	  campaignList.stream().filter(campaign->campaign.getLowerLimitOfApplyDiscount()<=products.size()).forEach(campaign->{products.forEach(product->applyDiscount(product,campaign));});
  }

 private void applyDiscount(Product product,Campaign campaign) {
	 double discount=(campaign.getDiscountType()==DiscountType.RATE?product.getPrice()*(campaign.getValueOfDiscount())/100:campaign.getValueOfDiscount());
	 product.setDiscountedPrice(product.getDiscountedPrice()-discount); 
 }

}
