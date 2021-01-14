package com.notonthehighstreet.promotion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import com.notonthehighstreet.Checkout;
import com.notonthehighstreet.CheckoutStandardTest;


/**
 * <code>BulkDiscountRuleTest</code> is a JUnit test case class.
 * 
 * It is for testing the promotion rule <code>BulkDiscountRule</code>. 
 * 
 * @author manfred
 *
 */
public class BulkDiscountRuleTest {

	
	private static final Logger logger = Logger.getLogger(CheckoutStandardTest.class.getName());
	
	
	/**
	 * Test case: All items 20% off.
	 */
	@Test
	public void testCase1()
	{
		PromotionRule[] rules = new PromotionRule[] {new BulkDiscountRule(new BigDecimal(0), 0.2)};
		Checkout checkout = new Checkout(Arrays.asList(rules));
		checkout.scan("001");
		checkout.scan("002");
		checkout.scan("003");
		logger.log(Level.INFO, "Total price expected: £" + checkout.getTotalPrice());
		assertEquals(59.36, checkout.total());
	}
	
	
	/**
	 * Test case: Spend over £100, 10% off.
	 */
	@Test
	public void testCase2()
	{
		PromotionRule[] rules = new PromotionRule[] {new BulkDiscountRule(new BigDecimal(100), 0.1)};
		Checkout checkout = new Checkout(Arrays.asList(rules));
		checkout.scan("001");
		checkout.scan("002");
		checkout.scan("003");
		logger.log(Level.INFO, "Total price expected: £" + checkout.getTotalPrice());
		assertEquals(74.2, checkout.total());
	}
	
	
	/**
	 * Test case: Spend over £50, 50% off.
	 */
	@Test
	public void testCase3()
	{
		PromotionRule[] rules = new PromotionRule[] {new BulkDiscountRule(new BigDecimal(50), 0.5)};
		Checkout checkout = new Checkout(Arrays.asList(rules));
		checkout.scan("002");
		checkout.scan("002");
		logger.log(Level.INFO, "Total price expected: £" + checkout.getTotalPrice());
		assertEquals(45, checkout.total());
	}
	
}
