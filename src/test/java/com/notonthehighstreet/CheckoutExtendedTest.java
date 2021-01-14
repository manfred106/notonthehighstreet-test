package com.notonthehighstreet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.*;

import com.notonthehighstreet.product.*;
import com.notonthehighstreet.promotion.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CheckoutExtendedTest {


	private static final Logger logger = Logger.getLogger(CheckoutStandardTest.class.getName());
	
	private List<PromotionRule> rules = new ArrayList<>();
	
	
	// Initialization of promotion rules
	public CheckoutExtendedTest()
	{
		// Add an additional item for testing.
		ProductStore store = ProductStore.getInstance();
		store.addProduct(new Product("004", "Inspirational Postcard", new BigDecimal(2)));
		
		// Discount rule: buy 2 or more travel card holders then the price drops to £8.50
		rules.add(new UnitPriceReductionRule("001", 2, new BigDecimal(8.5)));
		
		// Discount rule: Personalised cufflinks price reduces to £42.00
		rules.add(new UnitPriceReductionRule("002", 1, new BigDecimal(42)));
		
		// Discount rule: get 10% off
		rules.add(new BulkDiscountRule(new BigDecimal(0), 0.1));
		
		// Discount rule: spend over £80, get additional 5% off
		rules.add(new BulkDiscountRule(new BigDecimal(80), 0.05));
		
		// Discount rule: Buy 3 Inspirational Postcard to get 1 free.
		rules.add(new BuyAndGetFreeRule("004", 3, 2));
	}
	

	@Test
	@Order(10)
	public void test1() {
		Checkout checkout = new Checkout(rules);
		checkout.scan("001");
		checkout.scan("002");
		checkout.scan("003");
		logger.log(Level.INFO, "Total price expected: £" + checkout.getTotalPrice());
		assertEquals(64.08, checkout.total());
	}
	

	@Test
	@Order(20)
	public void test2() {
		Checkout checkout = new Checkout(rules);
		checkout.scan("002");
		checkout.scan("002");
		checkout.scan("003");
		logger.log(Level.INFO, "Total price expected: £" + checkout.getTotalPrice());
		assertEquals(88.88, checkout.total());
	}
	
	
	@Test
	@Order(30)
	public void test3() {
		Checkout checkout = new Checkout(rules);
		checkout.scan("004");
		checkout.scan("004");
		checkout.scan("004");
		checkout.scan("004");
		logger.log(Level.INFO, "Total price expected: £" + checkout.getTotalPrice());
		assertEquals(5.4, checkout.total());
	}
	
	
	@Test
	@Order(40)
	public void test4() {
		Checkout checkout = new Checkout(rules);
		checkout.scan("004");
		checkout.scan("004");
		checkout.scan("004");
		checkout.scan("004");
		checkout.scan("004");
		checkout.scan("004");
		checkout.scan("004");
		checkout.scan("004");
		checkout.scan("004");
		checkout.scan("004");
		checkout.scan("004");
		logger.log(Level.INFO, "Total price expected: £" + checkout.getTotalPrice());
		assertEquals(12.6, checkout.total());
	}
}
