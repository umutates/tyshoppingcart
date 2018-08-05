package com.shoppingcart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.entity.Product;
import com.shoppingcart.entity.ShoppingCart;
import com.shoppingcart.service.IProductService;
import com.shoppingcart.service.IShoppingCartCalculateService;
import com.shoppingcart.service.IShoppingCartService;

/**
 * @author umutates
 * created on 2018-08-04
 */

@RestController
@RequestMapping("/shopping")
public class ShoppingCartRestController {
	  
	  @Autowired
	  IShoppingCartService shoppingCartService;
	  
	  @Autowired
	  IProductService productService;
	  
	  @Autowired
	  IShoppingCartCalculateService cartCalculateService;

	  @PostMapping("/cart")
	  @ResponseStatus(HttpStatus.CREATED)
	  public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
		List<Product> products=new ArrayList<>();
        shoppingCart.getProducts().forEach(product ->products.add(productService.findById(product.getId())));
        shoppingCart.setProducts(products);
        shoppingCartService.createShoppingCart(shoppingCart);
      
        
	    return new ResponseEntity<>(shoppingCart, HttpStatus.CREATED);
	  }
	  
	   @GetMapping(value = "cart/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	   @ResponseStatus(HttpStatus.OK)
	   public ResponseEntity<ShoppingCart> getCart(@PathVariable Long id) {
		ShoppingCart shoppingCart=shoppingCartService.findById(id);
		   
		return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
	  }
	   
	   
	   @GetMapping(value = "/cart/calculate/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	   @ResponseStatus(HttpStatus.OK)
	   public ResponseEntity<ShoppingCart> calculateCartWithDiscount(@PathVariable Long id) {
		ShoppingCart shoppingCart=shoppingCartService.findById(id);
		cartCalculateService.calculate(shoppingCart);
		return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
	  }


}
