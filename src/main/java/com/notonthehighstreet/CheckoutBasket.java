package com.notonthehighstreet;

import java.math.BigDecimal;
import java.util.*;

import com.notonthehighstreet.product.Product;
import com.notonthehighstreet.product.ProductStore;
import com.notonthehighstreet.promotion.PromotionRule;

/**
 *  Checkout basket stores all the scanned items during the checkout process.
 *  
 * @author manfred
 *
 */
public class CheckoutBasket {

	private ProductStore store = ProductStore.getInstance();

	// Checkout item list that stores the actual product and the interim price
	// during the discount calculation.
	private List<CheckoutItem> itemList = new ArrayList<>();

	// Log all discounts that applied to this basket
	private List<DiscountApplied> discountList = new ArrayList<>();
	

	/**
	 * Add an item to the basket by product code
	 * 
	 * @param productCode Product code of the item.
	 */
	public void addItemByCode(String productCode) {
		Product product = store.getProductByCode(productCode);

		if (product != null) {
			itemList.add(new CheckoutItem(product));
		}
	}

	public List<CheckoutItem> getItemList() {
		return itemList;
	}

	
	/**
	 * Add a discount which is applicable to this basket.
	 * 
	 * @param rule The target promotional rule.
	 * @param beforePrice The basket price before applying the promotion.
	 * @param afterPrice The basket price after applying the promotion.
	 */
	public void addDiscountApplied(PromotionRule rule, BigDecimal beforePrice, BigDecimal afterPrice) {
		DiscountApplied discount = new DiscountApplied(rule, beforePrice, afterPrice);
		discountList.add(discount);
	}
	
	
	public List<DiscountApplied> getDiscountList()
	{
		return discountList;
	}
	

	/**
	 * Get the last recorded price after applying all discounts.
	 * 
	 * @return the last recorded price after applying all discounts
	 */
	public BigDecimal getLastPrice() {
		BigDecimal lastPrice = BigDecimal.ZERO;

		// The last discount applied logs the final price.
		if (!discountList.isEmpty()) {
			DiscountApplied discount = discountList.get(discountList.size() - 1);
			lastPrice = discount.getAfterPrice();
		}

		// No discount applied, just sum up all unit prices.
		else {
			for (CheckoutItem item : itemList) {
				lastPrice = lastPrice.add(item.getInterimPrice());
			}
		}

		return lastPrice;
	}

	/**
	 * Return whether this basket is empty.
	 * 
	 * @return whether this basket is empty.
	 */
	public boolean isEmpty() {
		return itemList.isEmpty();
	}

}