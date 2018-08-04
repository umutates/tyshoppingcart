package com.shoppingcart.util;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.shoppingcart.entity.Campaign;
import com.shoppingcart.entity.Category;
import com.shoppingcart.entity.Product;
import com.shoppingcart.repository.ICampaignRepository;
import com.shoppingcart.repository.ICategoryRepository;
import com.shoppingcart.repository.IProductRepository;

@Component
public class AppStartUp implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired 
    IProductRepository productRepository;
    
    @Autowired
    ICampaignRepository campaignRepository;
    
    @Autowired
    ICategoryRepository categoryRepository;
	
	
	public void dataLoads() {
		Category category=new Category("FOOD");
		category=categoryRepository.saveAndFlush(category);
		Product product=new Product("ProteinBar",new BigDecimal(10), category);
		productRepository.save(product);
		Campaign campaign=new Campaign(category, new BigDecimal(20),DiscountType.PERCENTAGE,3);
		campaignRepository.save(campaign);
	}


	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
	dataLoads();
	}
}
