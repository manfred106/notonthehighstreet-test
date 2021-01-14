package com.notonthehighstreet;

import java.math.BigDecimal;

/**
 * Helper class to handle common operations in the shopping example.
 * 
 * @author manfred
 *
 */
public class ShoppingHelper {

	/**
	 * Get the rounded price to the nearest 2 decimal places.
	 * 
	 * @param price Price to be rounded.
	 * @return the rounded price to the nearest 2 decimal places.
	 */
	public static BigDecimal roundPrice(BigDecimal price)
	{
		return (price != null) ? price.setScale(2, BigDecimal.ROUND_HALF_EVEN) : null;
	}
	
}