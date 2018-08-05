package com.shoppingcart.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.entity.Campaign;
import com.shoppingcart.entity.Coupon;
import com.shoppingcart.entity.Product;
import com.shoppingcart.entity.ResponseShoppingBill;
import com.shoppingcart.entity.ShoppingCart;
import com.shoppingcart.service.ICampaignService;
import com.shoppingcart.service.ICouponService;
import com.shoppingcart.service.IShoppingCartCalculateService;
import com.shoppingcart.service.IShoppingCartService;
import com.shoppingcart.util.DiscountType;

@Service
public class ShoppingCartCalculateService implements IShoppingCartCalculateService {
	
	@Autowired
	ICampaignService campaignService;
	
	@Autowired
	IShoppingCartService shoppingCartService;
	
	@Autowired
	ICouponService couponService;

	@Override
	public ResponseShoppingBill calculateCartWithinDiscount(ShoppingCart shoppingCart) {
		ResponseShoppingBill responseShoppingBill=new ResponseShoppingBill();
		shoppingCart.getProducts().forEach(product -> product.setDiscountedPrice(product.getPrice()));
		Map<Long,List<Product>> productsByCategory=getProductsByCategory(shoppingCart.getProducts());
		productsByCategory.forEach((k,v)->applyDiscountByGroup(v,k));
		shoppingCart.getProducts().forEach(product->{
			responseShoppingBill.setTotalAmount(responseShoppingBill.getTotalAmount()+product.getPrice());
			responseShoppingBill.setTotalAmountAfterDiscounts(responseShoppingBill.getTotalAmountAfterDiscounts()+product.getDiscountedPrice());

		});
	    responseShoppingBill.setCampaignDiscount(responseShoppingBill.getTotalAmount()-responseShoppingBill.getTotalAmountAfterDiscounts());
	    responseShoppingBill.setCouponDiscount(getCouponDiscount(responseShoppingBill.getTotalAmountAfterDiscounts(),shoppingCart.getCouponId()));
	    responseShoppingBill.setTotalAmountAfterDiscounts(responseShoppingBill.getTotalAmountAfterDiscounts()-responseShoppingBill.getCouponDiscount());
	    responseShoppingBill.setProductByCategory(productsByCategory);
		return responseShoppingBill;
  }
 
	private double getCouponDiscount(double cartAmount,Long couponId) {
		Coupon coupon=couponService.findById(couponId);
		if (coupon==null) {
			return 0;
		}
		double discount=cartAmount>coupon.getMinCartAmount()?(coupon.getDiscountType()==DiscountType.RATE?cartAmount*(coupon.getValueOfDiscount())/100:coupon.getValueOfDiscount()):0;
		return discount;
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
	  campaignList=campaignList.stream().filter(campaign->campaign.getLowerLimitOfApplyDiscount()<=products.size()).collect(Collectors.toList());
	  campaignList.forEach(campaign->{products.forEach(product->applyDiscount(product, campaign));});
  }

 private void applyDiscount(Product product,Campaign campaign) {
	 double discount=(campaign.getDiscountType()==DiscountType.RATE?product.getPrice()*(campaign.getValueOfDiscount())/100:campaign.getValueOfDiscount());
	 System.out.println(discount);
	 product.setDiscountedPrice(product.getDiscountedPrice()-discount); 
 }

}
