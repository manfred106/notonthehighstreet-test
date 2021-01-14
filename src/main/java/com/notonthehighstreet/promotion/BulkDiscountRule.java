package com.notonthehighstreet.promotion;

import java.math.BigDecimal;

import com.notonthehighstreet.CheckoutBasket;


/**
 * This class defines the bulk discount rule -> spending over the minimum amount
 * £, and getting a predefined % off.
 * 
 * @author manfred
 *
 */
public class BulkDiscountRule extends AbstractPromotionRule {

	private BigDecimal minAmount = BigDecimal.ZERO;

	private double discount = 0;

	public BulkDiscountRule() {
		setExecutionOrder(20);
	}

	public BulkDiscountRule(BigDecimal minAmount, double discount) {
		this();
		this.minAmount = minAmount;
		this.discount = discount;
	}

	public BigDecimal getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(BigDecimal minAmount) {
		this.minAmount = minAmount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		// Range check
		if (discount < 0 || discount > 1)
			throw new IllegalArgumentException("Discount must be between 0 and 1");

		this.discount = discount;
	}

	@Override
	public void apply(CheckoutBasket basket) {

		BigDecimal beforePrice = BigDecimal.ZERO;
		BigDecimal afterPrice = BigDecimal.ZERO;

		if (!basket.isEmpty()) {

			beforePrice = basket.getLastPrice();
			
			// Check whether price is greater than the minimum amount required.
			// If it is , this promotion is applicable to the current basket
			if (beforePrice.compareTo(minAmount) >= 0) {
				afterPrice = beforePrice.multiply(new BigDecimal(1- discount));
				basket.addDiscountApplied(this, beforePrice, afterPrice);
			}
		}
	}


	@Override
	public String toString() {
		return "Promotion 'Spend over £" + minAmount + ", get " + (discount * 100) + "% off";
	}

}