package com.notonthehighstreet.promotion;

import com.notonthehighstreet.CheckoutBasket;


/**
 * Interface of promotion rule.
 * 
 * @author manfred
 *
 */
public interface PromotionRule 
{

	String getName();
	
	void setName(String name);
	
	int getExecutionOrder();
	
	void setExecutionOrder(int order);
	
	/**
	 * Apply the promotion rule to the checkout basket
	 * 
	 * @param basket
	 */
	void apply(CheckoutBasket basket);
	
}
