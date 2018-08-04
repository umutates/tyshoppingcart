package com.shoppingcart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.shoppingcart.entity.Campaign;
import com.shoppingcart.repository.ICampaignRepository;
import com.shoppingcart.service.ICampaignService;

public class CampaignService implements ICampaignService {
    
	@Autowired
	ICampaignRepository campaignRepository;
	
	@Override
	public Campaign addCampaign(Campaign campaign) {
	return campaignRepository.save(campaign);
	}

}
