package com.cursomc.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double value;
	
	@ManyToMany 
	@JoinTable(name="PRODUCT_CATEGORY", joinColumns = @JoinColumn(name="product_id"), inverseJoinColumns=@JoinColumn(name="category_id"))
	@JsonBackReference
	private List<Category> categories = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="id.product")
	private Set<OrderedItem> items = new HashSet<>();
	
	public Product() {}
	
	public Product(Integer id, String name, Double value) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
	}
	
	//Tudo que começa com get é serializado.
	@JsonIgnore
	public List<Order> getOrders() {
		List<Order> orders = new ArrayList<>();
		
		for(OrderedItem item : items) {
			orders.add(item.getOrder());
		}
		
		return orders;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public List<Category> getCategories() {
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	public Set<OrderedItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderedItem> items) {
		this.items = items;
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", value=" + value + ", categories=" + categories + ", items="
				+ items + "]";
	}
	
	
	
	
	

}
