package com.shoppingcart.util;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = -7208697982176503531L;
	
	  public GenericException(Class<?> exceptionClass,String message) {
		    super(exceptionClass.getSimpleName() +":"+message);
		  }

}
