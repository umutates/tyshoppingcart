package com.shoppingcart.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.shoppingcart.dto.ResponseShopping;
import com.shoppingcart.entity.ShoppingCart;

@RestController
@RequestMapping("/shopping")
public class ShoppingRestController {
	
	  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	  @ResponseStatus(value=HttpStatus.OK)
	  public ResponseShopping calculateDiscounts(@Valid @RequestBody ShoppingCart cart) {

      return new ResponseShopping();
	  }

}
