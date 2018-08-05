package com.shoppingcart.servicetest;

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
import com.shoppingcart.repository.IProductRepository;
import com.shoppingcart.service.impl.ProductService;
import com.shoppingcart.util.GenericException;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
	@Mock
	IProductRepository productRepository;
	
	@InjectMocks
	ProductService productService;
	
	List<Product> products;
	
	List<Category> categories;
	
	@Before
	public void createDummyDate() {
		categories=Arrays.asList(new Category("FOOD"),new Category("PC"));
		products=Arrays.asList(new Product("ProteinBar1",10.0, categories.get(0)),
				               new Product("ProteinBar2",24.0,categories.get(0)), 
				               new Product("ProteinBar2",22.0,categories.get(1)));
	}
	
	 @Test(expected = GenericException.class)
	 public void shouldGenericExceptionWhenCallFindByNonExistingId() {
	    
		when(productRepository.findById(products.get(0).getId())).thenReturn(Optional.empty());
	   
	    productService.findById(products.get(0).getId());
	  }
	 
	 @Test
	 public void shoulGetProductWhenCallFindById() {
	    
		when(productRepository.findById(products.get(0).getId())).thenReturn(Optional.of(products.get(0)));
	   
	    Product testProduct=productService.findById(products.get(0).getId());
	    
	    assertThat(testProduct).isEqualTo(products.get(0));
	  }
	 
	 @Test
	 public void shouldGetAllProductWhenCallFindAll() {
		 
		 when(productRepository.findAll()).thenReturn(products);
		 
		 List<Product> result=productService.findAllProduct();
		 
		 assertThat(result).isEqualTo(result);
	 }
	 
	 @Test
	 public void shoulReturnProductWhenCallAddProduct() {
	    
		when(productRepository.save(products.get(0))).thenReturn(products.get(0));
	    
	    Product testProduct=productService.addProduct(products.get(0));
	    
	    assertThat(testProduct).isEqualTo(products.get(0));
	  }
	 
	 @Test(expected = GenericException.class)
	  public void sholdThrowGenericExceptionWhenNotExistingProduct() {

	    when(productRepository.findById(products.get(0).getId())).thenReturn(Optional.empty());

	    productService.deleteProduct(products.get(0).getId());
	  }

}
