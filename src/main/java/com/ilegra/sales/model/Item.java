package com.ilegra.sales.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Data, Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer quantity;

	private BigDecimal price;

	public Item() {
	}

	public Item(Long id, Integer quantity, BigDecimal price) {
		this.id = id;
		this.quantity = quantity;
		this.price = price;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getTotal() {
		return new BigDecimal(quantity).multiply(price);
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Item other = (Item) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", quantity=" + quantity + ", price=" + price + "]";
	}
}
