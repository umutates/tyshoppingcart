package com.shoppingcart.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.shoppingcart.entity.Campaign;
import com.shoppingcart.entity.Category;
import com.shoppingcart.entity.Coupon;
import com.shoppingcart.entity.Product;
import com.shoppingcart.entity.ResponseShoppingBill;
import com.shoppingcart.entity.ShoppingCart;
import com.shoppingcart.repository.IShoppingCartRepository;
import com.shoppingcart.service.impl.CampaignService;
import com.shoppingcart.service.impl.CouponService;
import com.shoppingcart.service.impl.ShoppingCartCalculateService;
import com.shoppingcart.service.impl.ShoppingCartService;
import com.shoppingcart.util.DiscountType;

/**
 * @author umutates
 * created on 2018-08-05
 */
@RunWith(MockitoJUnitRunner.class)
public class ShoppingCartCalculateServiceTest {

	@Mock
	IShoppingCartRepository shoppingCartRepository;
	
	@InjectMocks
	ShoppingCartCalculateService shoppingCartCalculateService;
	
	@MockBean
	ShoppingCartService shoppingCartService;
	
	@Mock
	CampaignService campaignService;
	
	@Mock
	CouponService couponService;
	
	List<Product> products;
	
	List<Category> categories;
	
	List<Campaign> campaigns;
	
	List<ShoppingCart> shoppingCarts;
	
	List<Coupon> coupons ;
  
	@Before
	public void createDummyDate() {
		categories=Arrays.asList(new Category("FOOD"),new Category("PC"));
		categories.get(0).setId(1L);
		categories.get(1).setId(2L);
		products=Arrays.asList(new Product("ProteinBar1",10.0, categories.get(0)),
				               new Product("ProteinBar2",24.0,categories.get(0)), 
				               new Product("ProteinBar3",22.0,categories.get(0)),
				               new Product("ProteinBar4",12.0,categories.get(1)),
				               new Product("ProteinBar5",11.0,categories.get(1)));
		
		campaigns=Arrays.asList(new Campaign(categories.get(0), 10, DiscountType.RATE,1),
	               new Campaign(categories.get(0), 3, DiscountType.AMOUNT,2),
	               new Campaign(categories.get(0), 20, DiscountType.RATE,1));
		shoppingCarts=Arrays.asList(new ShoppingCart(products,1L),new ShoppingCart(null,1L),new ShoppingCart(Arrays.asList(products.get(3),products.get(4)),1L));
		coupons=Arrays.asList(new Coupon(10, 20,DiscountType.RATE));
		
	}
	
	 @Test
	 public void shoulGetShoppingCartTotalAmountTrueWhenCallCalculateService() {
		
		 when(campaignService.findByCategoryId(Mockito.any())).thenReturn(campaigns);
		 when(couponService.findById(shoppingCarts.get(0).getCouponId())).thenReturn(coupons.get(0));
	    
		ResponseShoppingBill responseShoppingBill=shoppingCartCalculateService.calculateCartWithinDiscount(shoppingCarts.get(0));
	   
		assertThat(responseShoppingBill.getTotalAmount()).isEqualTo(56);
	    
	  }
	 
	 @Test
	 public void shoulGetShoppingCartTotalAmountAfterDiscountTrueWhenCallCalculateService() {
		
		 when(campaignService.findByCategoryId(Mockito.any())).thenReturn(campaigns);
		 when(couponService.findById(shoppingCarts.get(0).getCouponId())).thenReturn(coupons.get(0));
	    
		ResponseShoppingBill responseShoppingBill=shoppingCartCalculateService.calculateCartWithinDiscount(shoppingCarts.get(0));
	   
		assertThat(new BigDecimal(responseShoppingBill.getTotalAmountAfterDiscounts()).setScale(2,RoundingMode.HALF_UP).doubleValue()).isEqualTo(27.18);
	    
	  }
	 
	 @Test
	 public void shoulGetShoppingCartCampaingDiscountAmountTrueWhenCallCalculateService() {
		
		 when(campaignService.findByCategoryId(Mockito.any())).thenReturn(campaigns);
		 when(couponService.findById(shoppingCarts.get(0).getCouponId())).thenReturn(coupons.get(0));
	    
		ResponseShoppingBill responseShoppingBill=shoppingCartCalculateService.calculateCartWithinDiscount(shoppingCarts.get(0));
	   
		assertThat(new BigDecimal(responseShoppingBill.getCampaignDiscount()).setScale(2,RoundingMode.HALF_UP).doubleValue()).isEqualTo(25.80);
	    
	  }
	 
	 @Test
	 public void shoulGetShoppingCartCouponDiscountTrueWhenCallCalculateService() {
		
		 when(campaignService.findByCategoryId(Mockito.any())).thenReturn(campaigns);
		 when(couponService.findById(shoppingCarts.get(0).getCouponId())).thenReturn(coupons.get(0));
	    
		ResponseShoppingBill responseShoppingBill=shoppingCartCalculateService.calculateCartWithinDiscount(shoppingCarts.get(0));
	   
		assertThat(new BigDecimal(responseShoppingBill.getCouponDiscount()).setScale(2,RoundingMode.HALF_UP).doubleValue()).isEqualTo(3.02);
	    
	  }
	 
	 @Test
	 public void shouldNotThrowExceptionWhenCalculateServiceCartCallHasNotProduct() {
		
		ResponseShoppingBill responseShoppingBill=shoppingCartCalculateService.calculateCartWithinDiscount(shoppingCarts.get(1));
	   
		assertThat(responseShoppingBill.getTotalAmount()).isEqualTo(0.0);
	    
	  }
	 
	 @Test
	 public void shouldNotThrowExceptionWhenCalculateServiceCartCallHasNotCampaign() {
		when(couponService.findById(shoppingCarts.get(0).getCouponId())).thenReturn(coupons.get(0));
		ResponseShoppingBill responseShoppingBill=shoppingCartCalculateService.calculateCartWithinDiscount(shoppingCarts.get(2));
	   
		assertThat(responseShoppingBill.getTotalAmountAfterDiscounts()).isEqualTo(20.7);
	    
	  }
	 
	 
}
