package com.shoppingcart.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.shoppingcart.entity.Category;
import com.shoppingcart.entity.Product;
import com.shoppingcart.entity.ShoppingCart;
import com.shoppingcart.repository.IShoppingCartRepository;
import com.shoppingcart.service.impl.ShoppingCartService;
import com.shoppingcart.util.GenericException;

/**
 * @author umutates
 * created on 2018-08-05
 */
@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartServiceTest {
	
	@Mock
	IShoppingCartRepository shoppingCartRepository;
	
	@InjectMocks
	ShoppingCartService shoppingCartService;
	
	List<Product> products;
	
	List<Category> categories;
	
	List<ShoppingCart> shoppingCarts ;
  
	@Before
	public void createDummyDate() {
		categories=Arrays.asList(new Category("FOOD"),new Category("PC"));
		products=Arrays.asList(new Product("ProteinBar1",10.0, categories.get(0)),
				               new Product("ProteinBar2",24.0,categories.get(0)), 
				               new Product("ProteinBar2",22.0,categories.get(1)));
		shoppingCarts=Arrays.asList(new ShoppingCart(products,1L));
		
	}
	
	 @Test(expected = GenericException.class)
	 public void shouldGenericExceptionWhenCallFindByNonExistingId() {
	    
		when(shoppingCartRepository.findById(products.get(0).getId())).thenReturn(Optional.empty());
	   
	    shoppingCartService.findById(products.get(0).getId());
	  }
	 
	 @Test
	 public void shoulGetShoppingCartWhenCallFindById() {
	    
		when(shoppingCartRepository.findById(products.get(0).getId())).thenReturn(Optional.of(shoppingCarts.get(0)));
	   
	    ShoppingCart testShoppingCart=shoppingCartService.findById(shoppingCarts.get(0).getId());
	    
	    assertThat(testShoppingCart).isEqualTo(shoppingCarts.get(0));
	  }
	 
	 @Test
	 public void shoulReturnShoppingCartWhenCallAddShoppingCart() {
	    
		when(shoppingCartRepository.save(shoppingCarts.get(0))).thenReturn(shoppingCarts.get(0));
	    
	    ShoppingCart testShoppingCart=shoppingCartService.createShoppingCart(shoppingCarts.get(0));
	    
	    assertThat(testShoppingCart).isEqualTo(shoppingCarts.get(0));
	  }
	 

}
