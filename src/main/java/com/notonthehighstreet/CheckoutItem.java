package com.notonthehighstreet;

import java.math.BigDecimal;

import com.notonthehighstreet.product.Product;

public class CheckoutItem {

	
	private Product product;
	
	// Interim price stored for calculating discounts
	private BigDecimal interimPrice;
	
	
	public CheckoutItem() {
		
	}
	
	public CheckoutItem(Product product) {
		this();
		setProduct(product);
		setInterimPrice(product.getPrice());
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public BigDecimal getInterimPrice() {
		return interimPrice;
	}

	public void setInterimPrice(BigDecimal interimPrice) {
		this.interimPrice = interimPrice;
	}

	@Override
	public String toString() {
		return "ProductInterimPrice [product=" + product + ", interimPrice=" + interimPrice + "]";
	}
		
}