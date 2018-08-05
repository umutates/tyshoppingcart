package com.shoppingcart.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.entity.Campaign;
import com.shoppingcart.entity.Product;
import com.shoppingcart.entity.ShoppingCart;
import com.shoppingcart.service.ICampaignService;
import com.shoppingcart.service.IShoppingCartCalculateService;
import com.shoppingcart.service.IShoppingCartService;
import com.shoppingcart.util.ResponseShopping;

@Service
public class ShoppingCartCalculateService implements IShoppingCartCalculateService {
	
	@Autowired
	ICampaignService campaignService;
	
	@Autowired
	IShoppingCartService shoppingCartService;

	@Override
	public ResponseShopping calculate(ShoppingCart shoppingCart) {
		shoppingCart.getProducts().forEach(product -> product.setDiscountedPrice(product.getPrice()));
		groupByCategory(shoppingCart.getProducts());
		return null;
  }
 
	private Map<Long, List<Product>> groupByCategory(List<Product> products){
	Map<Long,List<Product>>productByGroupMap=new HashMap<>();
	products.stream().map(product -> product.getCategory().getId()).distinct()
		.forEach(categoryID -> {
		 List<Product> productByGroup=products.stream()
					.filter(product -> product.getCategory().getId().longValue() == categoryID.longValue()).collect(Collectors.toList());
		 productByGroupMap.put(categoryID, productByGroup);
		});
	  return productByGroupMap;
  }
 	
  
  private ShoppingCart applyDiscount(ShoppingCart cart,List<Campaign> discounts) {
	  
	  return null;
  }

//  private double calculateProductDiscount(Product product) {
//    Optional<Campaign> optionalCampaign =
//        campaignService.findByCategory(product.getCategory());
//
//    return calculateDiscount(optionalCampaign, product);
//  }
//
//  private double calculateCategoryDiscount(Product product) {
//    Optional<Campaign> optionalCampaign =
//        campaignRepository.findByCategoryId(product.getCategoryId());
//
//    return calculateDiscount(optionalCampaign, product);
//  }
//
//  private BigDecimal calculateDiscount(Optional<Campaign> optionalCampaign, Product product) {
//    if (!optionalCampaign.isPresent()) return BigDecimal.ZERO;
//    Campaign campaign = optionalCampaign.get();
//
//    BigDecimal discount;
//    if (campaign.getDiscountType().equals(DiscountType.RATE)) {
//      discount =
//          product.getPrice().multiply(campaign.getDiscountValue()).divide(new BigDecimal(100));
//
//      if (discount.compareTo(campaign.getMaximumDiscountPrice()) > 0) {
//        discount = campaign.getMaximumDiscountPrice();
//      }
//    } else {
//      discount = campaign.getDiscountValue();
//    }
//    return discount;
//  }
//
//  private void applyDiscountToProduct(Product product, double discount) {
//    double totalDiscount =product.getDiscountedPrice()-discount;
//    if (totalDiscount<0) {
//      product.setDiscountedPrice(0);
//    } else {
//      product.setDiscountedPrice(totalDiscount);
//    }
//  }

}
