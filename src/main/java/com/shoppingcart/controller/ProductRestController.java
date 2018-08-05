package com.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.entity.Category;
import com.shoppingcart.entity.Product;
import com.shoppingcart.service.ICategoryService;
import com.shoppingcart.service.IProductService;

@RestController
@RequestMapping("/product")
public class ProductRestController {
	
	  private final static String DELETE_MESSAGE="Delete operation success!";
	
	  @Autowired
	  IProductService productService;
	  
	  @Autowired
	  ICategoryService categoryService;
	  
	  @PostMapping()
	  @ResponseStatus(HttpStatus.CREATED)
	  public ResponseEntity<Product> add(@RequestBody Product product) {
        Category category=categoryService.findByTitle(product.getCategory().getTitle());
        product.setCategory(category);
	    product = productService.addProduct(product);

	    return new ResponseEntity<>(product, HttpStatus.CREATED);
	  }
	  
	  @DeleteMapping
	  @ResponseStatus(HttpStatus.OK)
	  public ResponseEntity<String> delete(@RequestBody Long id) {
        productService.deleteProduct(id);
	    return new ResponseEntity<>(DELETE_MESSAGE, HttpStatus.OK);
	  }

}
