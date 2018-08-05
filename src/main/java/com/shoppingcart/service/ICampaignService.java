package com.shoppingcart.service;

import java.util.List;

import com.shoppingcart.entity.Campaign;

public interface ICampaignService {
	public Campaign addCampaign(Campaign campaign);

	public List<Campaign> findByCategoryId(Long categoryId);
	

}
