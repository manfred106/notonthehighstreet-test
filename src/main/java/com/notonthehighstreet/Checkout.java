package com.notonthehighstreet;

import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.notonthehighstreet.promotion.PromotionRule;


/**
 * Checkout process. 
 * The constructor requires to input all promotion rules that will apply to the checkout process.
 * Calling getTotalPrice() triggers the checkout process.
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
	 * Get the original total price of all basket items.
	 * 
	 * @return the original total price.
	 */
	public BigDecimal getOriginalPrice()
	{
		return ShoppingHelper.roundPrice(basket.getOriginalPrice());
	}
	
	
	/**
	 * Get the total price after applying all discounts.
	 * 
	 * @return the total price after applying all discounts.
	 */
	public BigDecimal getTotalPrice()
	{
		BigDecimal total = BigDecimal.ZERO;
		
		if (!completed)
		{
			// Apply each promotional rule to the basket.
			// Calculate the discount amount of each rule after applying to the basket.
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
	
	
	/**
	 * Get the list of discount applied.
	 * 
	 * @return the list of discount applied.
	 */
	public List<DiscountApplied> getDiscountList()
	{
		return basket.getDiscountList();
	}
	
	
	/**
	 * Get the total price in double type.
	 * This method simply calls getTotalPrice(), convert its returned data type from BigDecimal to Double.
	 * 
	 * @return the total price in double type.
	 */
	public Double total()
	{
		return getTotalPrice().doubleValue();
	}
	
}