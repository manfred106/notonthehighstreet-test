package com.notonthehighstreet.promotion;

import java.math.BigDecimal;
import java.util.*;

import com.notonthehighstreet.CheckoutBasket;
import com.notonthehighstreet.CheckoutItem;


/**
 * UnitPriceReductionRule class is the implementation class of PromotionRule.
 * 
 * It models the promotion rule of buy (minimumUnits) or more items, then the unit price drops to (reducedPrice).
 * By providing different parameters to the constructor, it can handling different kind of unit price
 * reduction discounts.
 * 
 *  e.g. 
 *  1) new UnitPriceReductionRule("001", 0, 10) means "The product (product code=001) price reduces to £10".
 *  2) new UnitPriceReductionRule("001", 2, 8) means "Buy 2 or more product (product code=001), the price reduces to £8".
 *  
 * @author manfred
 *
 */
public class UnitPriceReductionRule extends AbstractPromotionRule {

	private String productCode;

	private int minimumUnits;

	private BigDecimal reducedPrice;

	public UnitPriceReductionRule() {
		// This rule should be run before the bulk discount rule, thus has a small order number
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
			
			List<CheckoutItem> itemList = basket.getItemList();
			for (CheckoutItem item : itemList) {
				
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
				
				// Make sure the price will not be higher than the original price after reduction.
				if (priceDiff.compareTo(BigDecimal.ZERO) > 0)
				{
					item.setInterimPrice(reducedPrice);
					afterPrice = afterPrice.subtract(priceDiff);
				}
			}
			
			basket.addDiscountApplied(this, beforePrice, afterPrice);
		}
	}


	@Override
	public String toString() {
		return "Buy " + getMinimumUnits() + " or more " + getProductCode() + ", unit price drops to £" + reducedPrice;
	}

}
