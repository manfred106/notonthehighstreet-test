package com.notonthehighstreet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.*;

import com.notonthehighstreet.promotion.BulkDiscountRule;
import com.notonthehighstreet.promotion.PromotionRule;
import com.notonthehighstreet.promotion.UnitPriceReductionRule;


/**
 * <code>CheckoutStandardTest</code> is a JUnit test case class.
 * 
 * It has 3 standard test cases covering all test data documented in the test. 
 * 
 * @author manfred
 *
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CheckoutStandardTest {


	private static final Logger logger = Logger.getLogger(CheckoutStandardTest.class.getName());
	
	private List<PromotionRule> rules = new ArrayList<>();
	
	
	// Initialization of promotion rules
	public CheckoutStandardTest()
	{
		// Discount rule: buy 2 or more travel card holders then the price drops to £8.50
		rules.add(new UnitPriceReductionRule("001", 2, new BigDecimal(8.5)));
		
		// Discount rule: spend over £60, get 10% off
		rules.add(new BulkDiscountRule(new BigDecimal(60), 0.1));
	}
	

	@Test
	@Order(10)
	public void test1() {
		Checkout checkout = new Checkout(rules);
		checkout.scan("001");
		checkout.scan("002");
		checkout.scan("003");
		logger.log(Level.INFO, "Total price expected: £" + checkout.getTotalPrice());
		assertEquals(66.78, checkout.total());
	}

	@Test
	@Order(20)
	public void test2() {
		
		Checkout checkout = new Checkout(rules);
		checkout.scan("001");
		checkout.scan("003");
		checkout.scan("001");
		logger.log(Level.INFO, "Total price expected: £" + checkout.getTotalPrice());
		assertEquals(36.95, checkout.total());
	}

	@Test
	@Order(30)
	public void test3() {
		Checkout checkout = new Checkout(rules);
		checkout.scan("001");
		checkout.scan("002");
		checkout.scan("001");
		checkout.scan("003");
		logger.log(Level.INFO, "Total price expected: £" + checkout.getTotalPrice());
		assertEquals(73.76, checkout.total());
	}

}
