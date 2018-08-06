package com.shoppingcart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shoppingcart.entity.Campaign;

/**
 * @author umutates
 * created on 2018-08-04
 */
@Service
public interface ICampaignService {
	public Campaign addCampaign(Campaign campaign);

	public List<Campaign> findByCategoryId(Long categoryId);

	public List<Campaign> findAllCampaign();
	

}
