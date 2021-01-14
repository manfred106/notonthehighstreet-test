package com.notonthehighstreet;

import java.math.BigDecimal;

import com.notonthehighstreet.promotion.PromotionRule;

/**
 * DiscountApplied stores the entry of applying PromotionRule to the
 * CheckoutBasket.
 * 
 * There are 3 information stored in this class:
 * 
 * - Price before applying the promotion - Price after applying the promotion -
 * The promotion rule applied
 * 
 * @author manfred
 *
 */
public class DiscountApplied {

	private PromotionRule rule;

	private BigDecimal beforePrice;

	private BigDecimal afterPrice;

	
	public DiscountApplied(PromotionRule rule, BigDecimal beforePrice, BigDecimal afterPrice) {
		this.rule = rule;
		this.beforePrice = beforePrice;
		this.afterPrice = afterPrice;
	}

	public PromotionRule getRule() {
		return rule;
	}

	public BigDecimal getBeforePrice() {
		return beforePrice;
	}

	public BigDecimal getAfterPrice() {
		return afterPrice;
	}

	public BigDecimal getDiscountAmount() {
		return beforePrice.subtract(afterPrice);
	}

	@Override
	public String toString() {
		return "DiscountApplied [rule=" + rule + ", beforePrice=" + beforePrice + ", afterPrice=" + afterPrice + "]";
	}

}