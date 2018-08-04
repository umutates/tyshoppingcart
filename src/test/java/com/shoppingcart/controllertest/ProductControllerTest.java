package com.shoppingcart.controllertest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shoppingcart.controller.ProductRestController;
import com.shoppingcart.service.IProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductRestController.class, secure = false)
public class ProductControllerTest {
	  @Autowired 
	  private MockMvc mockMvc;
	  
	  @MockBean 
	  private IProductService productService;
	
	 private static ObjectMapper mapper = new ObjectMapper();
	 @Test
	  public void should_httpOK_when_addProduct() throws Exception {
	    String requestPayload =
	        "{ \"title\": \"Puskevit\", \"price\": \"10\", \"category\":\"3\"}";

	    RequestBuilder requestBuilder =
	        MockMvcRequestBuilders.post("/product")
	            .content(requestPayload)
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON);
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    MockHttpServletResponse response = result.getResponse();

	    assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	  }
}
