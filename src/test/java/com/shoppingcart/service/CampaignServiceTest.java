package com.shoppingcart.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.shoppingcart.entity.Campaign;
import com.shoppingcart.entity.Category;
import com.shoppingcart.repository.ICampaignRepository;
import com.shoppingcart.service.impl.CampaignService;
import com.shoppingcart.util.DiscountType;
import com.shoppingcart.util.GenericException;

/**
 * @author umutates
 * created on 2018-08-05
 */
@RunWith(MockitoJUnitRunner.class)
public class CampaignServiceTest {

	@Mock
	ICampaignRepository campaignRepository;
	
	@InjectMocks
	CampaignService campaignService;
	
	List<Campaign> campaigns;
	
	List<Campaign> allCampaigns;
	
	List<Category> categories;
	
	@Before
	public void createDummyDate() {
		categories=Arrays.asList(new Category("FOOD"),new Category("PC"));
		campaigns=Arrays.asList(new Campaign(categories.get(0), 10, DiscountType.RATE,1),
				               new Campaign(categories.get(0), 15, DiscountType.AMOUNT,2),
				               new Campaign(categories.get(0), 20, DiscountType.RATE,1));
		allCampaigns=Arrays.asList(new Campaign(categories.get(0), 10, DiscountType.RATE,1),
	                               new Campaign(categories.get(0), 15, DiscountType.AMOUNT,2),
	                               new Campaign(categories.get(1), 20, DiscountType.RATE,1),
	                               new Campaign(categories.get(1), 20, DiscountType.RATE,1));
	}
	
	 @Test
	 public void shoulGetCampaignWhenCallFindById() {
	    
		when(campaignRepository.findByCategoryId(categories.get(0).getId())).thenReturn(campaigns);
	   
	    List<Campaign> testCampaign=campaignService.findByCategoryId(categories.get(0).getId());
	    
	    assertThat(testCampaign).isEqualTo(campaigns);
	  }
	 
	 @Test
	 public void shouldGetAllCampaignWhenCallFindAll() {
		 
		 when(campaignRepository.findAll()).thenReturn(allCampaigns);
		 
		 List<Campaign> result=campaignService.findAllCampaign();
		 
		 assertThat(result).isEqualTo(allCampaigns);
	 }
	 
	 @Test
	 public void shoulReturnCampaignWhenCallAddCampaign() {
	    
		when(campaignRepository.save(campaigns.get(0))).thenReturn(campaigns.get(0));
	    
	    Campaign testCampaign=campaignService.addCampaign(campaigns.get(0));
	    
	    assertThat(testCampaign).isEqualTo(campaigns.get(0));
	  }
	 
}
