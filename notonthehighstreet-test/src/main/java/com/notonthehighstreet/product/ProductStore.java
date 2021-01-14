package com.notonthehighstreet.product;

import java.math.BigDecimal;
import java.util.*;

/**
 * ProductStore is a singleton class that stores all available product in the
 * site.
 * 
 * @author manfred
 *
 */
public class ProductStore {

	private static ProductStore instance;

	private Map<String, Product> productMap = new HashMap<>();

	/**
	 * Get the instance of ProductStore.
	 * 
	 * @return instance of ProductStore
	 */
	public static synchronized ProductStore getInstance() {
		if (instance == null)
			instance = new ProductStore();
		return instance;
	}

	/**
	 * Initialization of ProductStore. Add all available products to the shelf.
	 */
	protected ProductStore() {
		addProduct(new Product("001", "Travel Card Holder", new BigDecimal(9.25)));
		addProduct(new Product("002", "Personalised cufflinks", new BigDecimal(45)));
		addProduct(new Product("003", "Kids T-shirt", new BigDecimal(19.95)));
	}

	public void addProduct(Product product) {
		productMap.put(product.getCode(), product);
	}

	public Product getProductByCode(String code) {
		return productMap.get(code);
	}

}
