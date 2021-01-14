package com.notonthehighstreet.promotion;

/**
 * Abstract class of promotion rule with the standard getter and setter implementation 
 * for name and executionOrder field.
 * 
 * @author manfred
 *
 */
public abstract class AbstractPromotionRule implements PromotionRule {

	// Promotion name
	private String name;
	
	// Execution order
	private int executionOrder;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getExecutionOrder() {
		return executionOrder;
	}

	@Override
	public void setExecutionOrder(int executionOrder) {
		this.executionOrder = executionOrder;
	}

}