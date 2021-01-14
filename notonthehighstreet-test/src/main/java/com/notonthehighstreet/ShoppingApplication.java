package com.notonthehighstreet;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import com.notonthehighstreet.promotion.BulkDiscountRule;
import com.notonthehighstreet.promotion.PromotionRule;
import com.notonthehighstreet.promotion.UnitPriceReductionRule;

/**
 * 
 * Main application resides here. 
 * 
 * It will ask for a input of all basket items (e.g. 001,002,003) when it runs.
 * 
 * @author manfred
 *
 */
public class ShoppingApplication {
	
	
	public static void main(String[] args) throws IOException {
		
		// Add all promotion rules
		List<PromotionRule> rules = new ArrayList<>();
		rules.add(new UnitPriceReductionRule("001", 2, new BigDecimal(8.5)));
		rules.add(new BulkDiscountRule(new BigDecimal(60), 0.1));
		
		Checkout checkout = new Checkout(rules);
		
		System.out.print("Basket: ");
		
		// Read a line from standard in
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		String input = reader.readLine();
		
		if (input != null)
		{
			String[] itemCodes = input.split(",");
			Arrays.stream(itemCodes).forEach(code -> checkout.scan(code));
		}
		
		System.out.println("Total price expected: £" + checkout.getTotalPrice());
	}
	
}
