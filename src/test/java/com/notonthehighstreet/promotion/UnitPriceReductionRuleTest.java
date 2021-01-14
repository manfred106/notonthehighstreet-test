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
 * <code>UnitPriceReductionRuleTest</code> is a JUnit test case class.
 * 
 * It is for testing the promotion rule <code>UnitPriceReductionRule</code>. 
 * 
 * @author manfred
 *
 */
public class UnitPriceReductionRuleTest {

	
	private static final Logger logger = Logger.getLogger(CheckoutStandardTest.class.getName());
	
	
	/**
	 * Test case: All Travel Card Holder (productCode:001) reduced to £9
	 */
	@Test
	public void testCase1()
	{
		PromotionRule[] rules = new PromotionRule[] {new UnitPriceReductionRule("001", 1, new BigDecimal(9))};
		Checkout checkout = new Checkout(Arrays.asList(rules));
		checkout.scan("001");
		checkout.scan("002");
		checkout.scan("003");
		logger.log(Level.INFO, "Total price expected: £" + checkout.getTotalPrice());
		assertEquals(73.95, checkout.total());
	}
	
	
	/**
	 * Test case: buy 3 or more travel card holders then the price drops to £8
	 */
	@Test
	public void testCase2()
	{
		PromotionRule[] rules = new PromotionRule[] {new UnitPriceReductionRule("001", 3, new BigDecimal(8))};
		Checkout checkout = new Checkout(Arrays.asList(rules));
		checkout.scan("001");
		checkout.scan("001");
		checkout.scan("001");
		checkout.scan("001");
		logger.log(Level.INFO, "Total price expected: £" + checkout.getTotalPrice());
		assertEquals(32, checkout.total());
	}
	
	
	/**
	 * Test case: buy 2 or more travel card holders then the price drops to £8.5
	 */
	@Test
	public void testCase3()
	{
		PromotionRule[] rules = new PromotionRule[] {new UnitPriceReductionRule("001", 2, new BigDecimal(8.5))};
		Checkout checkout = new Checkout(Arrays.asList(rules));
		checkout.scan("001");
		checkout.scan("002");
		checkout.scan("003");
		logger.log(Level.INFO, "Total price expected: £" + checkout.getTotalPrice());
		assertEquals(74.2, checkout.total());
	}
	
}
