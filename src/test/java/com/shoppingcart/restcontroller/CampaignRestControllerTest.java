package com.shoppingcart.restcontroller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingcart.controller.CampaignRestController;
import com.shoppingcart.entity.Campaign;
import com.shoppingcart.entity.Category;
import com.shoppingcart.service.ICampaignService;
import com.shoppingcart.service.ICategoryService;
import com.shoppingcart.util.DiscountType;

/**
 * @author umutates
 * created on 2018-08-05
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = CampaignRestController.class)
public class CampaignRestControllerTest {
	  @Autowired 
	  private MockMvc mockMvc;
	  
	  @MockBean 
	  private ICampaignService campaignService;
	  
	  @MockBean
	  private ICategoryService categoryService;
	 
	  private static ObjectMapper mapper = new ObjectMapper();
	  
	  List<Campaign> campaigns;
		
	  List<Category> categories;
	  
	  @Before
		public void createDummyDate() {
			categories=Arrays.asList(new Category("FOOD"),new Category("PC"));
			campaigns=Arrays.asList(new Campaign(categories.get(0),20.0,DiscountType.RATE,1),
					                new Campaign(categories.get(1),20.0,DiscountType.RATE,1));
		}
	  
	 @Test
	  public void should_httpOK_when_addCampaign() throws Exception {
	    String request = "{ \"discountType\": \"RATE\", \"valueOfDiscount\": \"20.0\",\"lowerLimitOfApplyDiscount\":\"1\", \"category\":{\"title\":\"FOOD\"}}";
	    String responseContent = mapper.writeValueAsString(campaigns.get(0));
		when(campaignService.findByCategoryId(categories.get(0).getId())).thenReturn(Arrays.asList(campaigns.get(0)));
		when(campaignService.addCampaign(Mockito.any())).thenReturn(campaigns.get(0));
		RequestBuilder requestBuilder =
	        MockMvcRequestBuilders.post("/campaign")
	            .content(request)
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON);
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    MockHttpServletResponse response = result.getResponse();

	    assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	    assertEquals(responseContent, response.getContentAsString());
	  }
	 
	 @Test
	  public void should_httpBadRequest_when_addCampaign() throws Exception {
	    String request = "{ \"a\": \"b\", \"}}";
	    RequestBuilder requestBuilder =
	        MockMvcRequestBuilders.post("/campaign")
	            .content(request)
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON);
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    MockHttpServletResponse response = result.getResponse();

	    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	  }
}
