package com.notonthehighstreet.promotion;

import java.math.BigDecimal;
import java.util.*;

import com.notonthehighstreet.CheckoutBasket;
import com.notonthehighstreet.CheckoutItem;


public class UnitPriceReductionRule extends AbstractPromotionRule {

	private String productCode;

	private int minimumUnits;

	private BigDecimal reducedPrice;

	public UnitPriceReductionRule() {
		setExecutionOrder(10);
	}

	public UnitPriceReductionRule(String productCode, int minimumUnits, BigDecimal reducedPrice) {
		this();
		setProductCode(productCode);
		setMinimumUnits(minimumUnits);
		setReducedPrice(reducedPrice);
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getMinimumUnits() {
		return minimumUnits;
	}

	public void setMinimumUnits(int minimumUnits) {
		this.minimumUnits = minimumUnits;
	}

	public BigDecimal getReducedPrice() {
		return reducedPrice;
	}

	public void setReducedPrice(BigDecimal reducedPrice) {
		this.reducedPrice = reducedPrice;
	}

	@Override
	public void apply(CheckoutBasket basket) {

		BigDecimal beforePrice = BigDecimal.ZERO;
		BigDecimal afterPrice = BigDecimal.ZERO;

		int unitCount = 0;
		List<CheckoutItem> affectedItemList = null;
		
		if (!basket.isEmpty()) {
			
			affectedItemList = new ArrayList<>();
			
			for (CheckoutItem item : basket.getItemList()) {
				
				beforePrice = beforePrice.add(item.getInterimPrice());
				
				if (!productCode.equalsIgnoreCase(item.getProduct().getCode()))
					continue;

				affectedItemList.add(item);
				unitCount++;
			}
		}
		
		// The unit code matches the minimum requirement, apply the promotion to the basket
		if (unitCount >= minimumUnits)
		{
			afterPrice = basket.getLastPrice();
			for (CheckoutItem item : affectedItemList)
			{
				BigDecimal priceDiff = item.getInterimPrice().subtract(reducedPrice);
				item.setInterimPrice(reducedPrice);
				afterPrice = afterPrice.subtract(priceDiff);
			}
			
			basket.addDiscountApplied(this, beforePrice, afterPrice);
		}
	}


	@Override
	public String toString() {
		return "Buy " + getMinimumUnits() + " or more " + getProductCode() + ", unit price drops to £" + reducedPrice;
	}

}
