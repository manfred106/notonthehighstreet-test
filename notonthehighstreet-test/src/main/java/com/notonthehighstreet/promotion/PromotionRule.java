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
	 * Apply the promotion rule to the checkout basket.
	 * This needs to be implemented by the concrete class to handle the actual promotion business logic.
	 * 
	 * @param basket
	 */
	void apply(CheckoutBasket basket);
	
}
