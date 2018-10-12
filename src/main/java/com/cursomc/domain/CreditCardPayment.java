package com.cursomc.domain;

import javax.persistence.Entity;

import com.cursomc.constants.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@JsonTypeName("creditCardPayment")
public class CreditCardPayment extends Payment {
	
	private Integer instalments;
	
	public CreditCardPayment() {}

	public CreditCardPayment(Integer id, PaymentStatus status, Order order, Integer instalments) {
		super(id, status, order);
		this.instalments = instalments;
	}

	public Integer getInstalments() {
		return instalments;
	}

	public void setInstalments(Integer instalments) {
		this.instalments = instalments;
	}
	
	
	

}
