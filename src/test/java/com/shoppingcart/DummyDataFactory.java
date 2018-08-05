package com.shoppingcart;

import java.util.Arrays;
import java.util.List;

import com.shoppingcart.entity.Category;

public class DummyDataFactory {

	public static List<Category> getCategoryList() {
		return Arrays.asList(new Category("FOOD"),new Category("PC"));
	}
	
}
