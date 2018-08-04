package com.shoppingcart;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.shoppingcart.entity.Category;
import com.shoppingcart.entity.Product;
import com.shoppingcart.repository.IProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TyshoppingcartApplicationTests {
    
	@Autowired IProductRepository productRepository;
	
	
	@Test
	public void contextLoads() {
		Category category=new Category("FOOD");
		Product product=new Product("ProteinBar",new BigDecimal(10), category);
		productRepository.save(product);
	}

}
