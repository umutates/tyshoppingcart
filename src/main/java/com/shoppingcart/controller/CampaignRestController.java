package com.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.entity.Campaign;
import com.shoppingcart.entity.Category;
import com.shoppingcart.service.ICampaignService;
import com.shoppingcart.service.ICategoryService;

/**
 * @author umutates
 * created on 2018-08-04
 */
@RestController
@RequestMapping("/campaign")
public class CampaignRestController {
	
	  @Autowired
	  ICampaignService campaignService;
	  
	  @Autowired
	  ICategoryService categoryService;
	
	  @PostMapping()
	  @ResponseStatus(HttpStatus.CREATED)
	  public ResponseEntity<Campaign> add(@RequestBody Campaign campaign) { 
		Category category=categoryService.findByTitle(campaign.getCategory().getTitle());
	    campaign.setCategory(category);  
        campaign=campaignService.addCampaign(campaign);
		 
	    return new ResponseEntity<>(campaign, HttpStatus.CREATED);
	  }

}
