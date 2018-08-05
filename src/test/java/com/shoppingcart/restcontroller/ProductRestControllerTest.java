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
import com.shoppingcart.controller.ProductRestController;
import com.shoppingcart.entity.Category;
import com.shoppingcart.entity.Product;
import com.shoppingcart.service.ICategoryService;
import com.shoppingcart.service.IProductService;

/**
 * @author umutates
 * created on 2018-08-05
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductRestController.class)
public class ProductRestControllerTest {
	  @Autowired 
	  private MockMvc mockMvc;
	  
	  @MockBean 
	  private IProductService productService;
	  
	  @MockBean
	  private ICategoryService categoryService;
	 
	  private static ObjectMapper mapper = new ObjectMapper();
	  
	  List<Product> products;
		
	  List<Category> categories;
	  
	  @Before
		public void createDummyDate() {
			categories=Arrays.asList(new Category("FOOD"),new Category("PC"));
			products=Arrays.asList(new Product("ProteinBar1",10.0, categories.get(0)),
					               new Product("ProteinBar2",24.0,categories.get(0)), 
					               new Product("ProteinBar2",22.0,categories.get(1)));
		}
	  
	 @Test
	  public void should_httpOK_when_addProduct() throws Exception {
	    String request = "{ \"title\": \"ProteinBar1\", \"price\": \"10.0\", \"category\":{\"title\":\"FOOD\"}}";
	    String responseContent = mapper.writeValueAsString(products.get(0));
		when(categoryService.findByTitle(categories.get(0).getTitle())).thenReturn(categories.get(0));
		when(productService.addProduct(Mockito.any())).thenReturn(products.get(0));
	    RequestBuilder requestBuilder =
	        MockMvcRequestBuilders.post("/product")
	            .content(request)
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON);
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    MockHttpServletResponse response = result.getResponse();

	    assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	    assertEquals(responseContent, response.getContentAsString());
	  }
	 
	 @Test
	  public void should_httpBadRequest_when_addProduct() throws Exception {
	    String request = "{ \"title\": \"ProteinBar1\", \"}}";
	    RequestBuilder requestBuilder =
	        MockMvcRequestBuilders.post("/product")
	            .content(request)
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON);
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    MockHttpServletResponse response = result.getResponse();

	    assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	  }
}
