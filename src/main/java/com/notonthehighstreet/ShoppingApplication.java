package com.notonthehighstreet;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

import com.notonthehighstreet.promotion.BulkDiscountRule;
import com.notonthehighstreet.promotion.PromotionRule;
import com.notonthehighstreet.promotion.UnitPriceReductionRule;


/**
 * ShoppingApplication is the starting point of the whole application.
 * 
 * Running this application prompts the user to input a list of product codes.
 * Checkout process that calculates the expected price after applying promotion rules.
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
		
		System.out.print("Basket: ");
		
		// Read the basket items from standard input
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		String input = reader.readLine();
		
		Checkout checkout = new Checkout(rules);
		
		// Delimit the input and convert it to the list of product codes.
		if (input != null) {
			String[] itemCodes = input.split(",");
			Arrays.stream(itemCodes).forEach(code -> checkout.scan(code));
		}

		System.out.println("Total price expected: £" + checkout.getTotalPrice());
	}
	
}