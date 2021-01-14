package com.notonthehighstreet.promotion;

import com.notonthehighstreet.CheckoutBasket;


/**
 * <code>PromotionRule</code> is an interface that all promotion rules should implement it.
 * 
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
	 * @param basket <code>CheckoutBasket</code> instance.
	 */
	void apply(CheckoutBasket basket);
	
}
