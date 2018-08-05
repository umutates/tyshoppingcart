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
		Category category1=new Category("FOOD");
		category1=categoryRepository.saveAndFlush(category1);
		Category category2=new Category("PC");
		category2=categoryRepository.saveAndFlush(category2);
		Product product1=new Product("ProteinBar1",10.0, category1);
		productRepository.save(product1);
		Product product2=new Product("ProteinBar2",11.0, category1);
		productRepository.save(product2);
		Product product3=new Product("ProteinBar3",12.0, category2);
		productRepository.save(product3);
		Campaign campaign=new Campaign(category1,20.0,DiscountType.RATE,1);
		campaignRepository.save(campaign);
	}


	@Override
	public void onApplicationEvent(ApplicationReadyEvent arg0) {
	dataLoads();
	}
}
