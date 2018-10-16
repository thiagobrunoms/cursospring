package com.cursomc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderedItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId				
	private FKOrderedItem id = new FKOrderedItem();
	
	private Double discount;
	private Integer quantity;
	private Double price;
	
	public OrderedItem() {}
	
	public OrderedItem(Order order, Product product, Double discount, Integer quantity, Double price) {
		super();
		this.id.setOrder(order);
		this.id.setProduct(product);
		this.discount = discount;
		this.quantity = quantity;
		this.price = price;
	}
	
	public double getSubTotal() {
		return (price - discount) * quantity;
	}
	
	@JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}
	
	public Product getProduct() {
		return id.getProduct();
	}

	public void setOrder(Order order) {
		this.id.setOrder(order);
	}
	
	public void setProduct(Product product) {
		this.id.setProduct(product);
	}
	
	public FKOrderedItem getId() {
		return id;
	}

	public void setId(FKOrderedItem oi) {
		this.id = oi;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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
		OrderedItem other = (OrderedItem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder builder = new StringBuilder();
		builder.append("OrderedItem [id=");
		builder.append(id);
		builder.append("Product: ");
		builder.append(getProduct().getName());
		builder.append(", discount=");
		builder.append(discount);
		builder.append(", quantity=");
		builder.append(quantity);
		builder.append(", unit price=");
		builder.append(price);
		builder.append(" subtotal = ");
		builder.append(nf.format(getSubTotal()));
		builder.append("]\n");
		return builder.toString();
	}
	
	
	
}
