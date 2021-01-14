package com.notonthehighstreet;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.notonthehighstreet.promotion.PromotionRule;


/**
 * Checkout process
 * 
 * @author manfred
 *
 */
public class Checkout { 
	
	private static final Comparator<PromotionRule> COMPARATOR_RULE = Comparator.comparing(PromotionRule::getExecutionOrder);
	
	private static final Logger logger = Logger.getLogger(Checkout.class.getName());
	
	private CheckoutBasket basket;
	
	private boolean completed = false;
	
	private List<PromotionRule> ruleList;
	
	
	public Checkout(List<PromotionRule> ruleList)
	{
		this.ruleList = ruleList;
		this.basket = new CheckoutBasket();
		
		// Sort the rule list according to the execution order
		Collections.sort(this.ruleList, COMPARATOR_RULE);
	}
	

	/**
	 * Scan the product by the product code, put it into the checkout basket.
	 * 
	 * @param productCode Product code of the product.
	 */
	public void scan(String productCode)
	{
		if (productCode != null)
		{
			basket.addItemByCode(productCode.trim());
		}
	}
	
	
	/**
	 * Get the total price after applying all discounts.
	 * 
	 * @return
	 */
	public BigDecimal getTotalPrice()
	{
		BigDecimal total = BigDecimal.ZERO;
		
		if (!completed)
		{
			// Apply the discount of each promotional rule
			// Calculate the reduction of each rule after apply to the shopping cart.
			if (ruleList != null)
			{
				ruleList.stream().forEach(r -> r.apply(basket));
			}
			
			// Set this flag to true to avoid re-apply promotion rules when calling getTotalPrice()
			completed = true;
		}
		
		total = ShoppingHelper.roundPrice(basket.getLastPrice());
		return total;
	}
	
	
	public List<DiscountApplied> getDiscountList()
	{
		return basket.getDiscountList();
	}
	
	/**
	 * Get the total price in double type.
	 * 
	 * @return
	 */
	public Double total()
	{
		return getTotalPrice().doubleValue();
	}
	
}