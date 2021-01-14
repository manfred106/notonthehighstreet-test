package com.notonthehighstreet.product;

import java.math.BigDecimal;

/**
 * A POJO class for storing the details of the product including product code,
 * name and price. Product code is the unique identifier to identify the product.
 * 
 * @author manfred
 *
 */
public class Product {

	private String code;

	private String name;

	// Dealing with money. Thus, use BigDecimal instead of float or double.
	private BigDecimal price;

	public Product() {
	}

	public Product(String code, String name, BigDecimal price) {
		this();
		this.code = code;
		this.name = name;
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [code=" + code + ", name=" + name + ", price=" + price + "]";
	}

}