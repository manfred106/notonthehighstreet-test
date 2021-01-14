package com.notonthehighstreet.promotion;

import java.math.BigDecimal;

import com.notonthehighstreet.CheckoutBasket;
import com.notonthehighstreet.CheckoutItem;


/**
 * BuyAndGetFreeRule class models the promotion rule of buy x get y free on the same product.
 * 
 * @author manfred
 *
 */
public class BuyAndGetFreeRule extends AbstractPromotionRule {

	private String productCode;
	
	private int numX;
	
	private int numY;
	

	public BuyAndGetFreeRule() {
		setExecutionOrder(10);
	}

	public BuyAndGetFreeRule(String productCode, int numX, int numY) {
		this();
		setProductCode(productCode);
		setNumX(numX);
		setNumY(numY);
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getNumX() {
		return numX;
	}

	public void setNumX(int numX) {
		this.numX = numX;
	}

	public int getNumY() {
		return numY;
	}

	public void setNumY(int numY) {
		this.numY = numY;
	}

	@Override
	public void apply(CheckoutBasket basket) {

		BigDecimal beforePrice = BigDecimal.ZERO;
		BigDecimal afterPrice = BigDecimal.ZERO;

		// This is for recording the number of item of the target product in the basket
		int itemCount = 0;
		
		if (!basket.isEmpty()) {
			
			BigDecimal discountAmount = BigDecimal.ZERO;
			int freeCount = 0;
			
			for (CheckoutItem item : basket.getItemList()) {
				
				// Calculate the total price before promotion
				beforePrice = beforePrice.add(item.getInterimPrice());

				// This one is the promotional product of this rule
				if (productCode.equalsIgnoreCase(item.getProduct().getCode())) {

					// Free item
					if (freeCount > 0) {
						freeCount--;
						discountAmount.subtract(item.getInterimPrice());
						item.setInterimPrice(BigDecimal.ZERO);
					} 
					
					// Item count
					else {
						itemCount++;

						// Match the minimum count, award free item(s).s
						if (itemCount == numX) {
							itemCount = 0;
							freeCount += numY;
						}
					}
				}
			}
			
			afterPrice = basket.getLastPrice().subtract(discountAmount);
			basket.addDiscountApplied(this, beforePrice, afterPrice);
		}
	}


	@Override
	public String toString() {
		return "Buy " + getNumX() + " " + getProductCode() + 
				" to get " + getNumY() + " free";
	}

}