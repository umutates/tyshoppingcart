package com.shoppingcart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingcart.entity.Campaign;
import com.shoppingcart.repository.ICampaignRepository;
import com.shoppingcart.service.ICampaignService;

@Service
public class CampaignService implements ICampaignService {
    
	@Autowired
	ICampaignRepository campaignRepository;
	
	@Override
	public Campaign addCampaign(Campaign campaign) {
	return campaignRepository.save(campaign);
	}

	@Override
	public List<Campaign> findByCategoryId(Long categoryId) {
    return campaignRepository.findByCategoryId(categoryId);
	}

}
