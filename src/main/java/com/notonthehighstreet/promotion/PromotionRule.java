package com.notonthehighstreet.promotion;

import com.notonthehighstreet.CheckoutBasket;


/**
 * <code>PromotionRule</code> is an interface that provide the abstraction for all promotion rules.
 * All concrete promotion rule classes should implement it.
 * 
 * getName() is expected to return the promotion name such as "Spend over £60, get 10% off."
 * getExecutionOrder() is expected to return the execution order of promotion rule. Promotion rules apply to the
 * basket in the checkout basket in ascending execution order (i.e. smaller value applies first).
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
