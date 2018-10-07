package com.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.cursomc.constants.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class TicketPayment extends Payment {
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date deadlineDate;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date paymentDate;
	
	public TicketPayment() {}
	public TicketPayment(Integer id, PaymentStatus status, Order order, Date deadlineDate, Date paymentDate) {
		super(id, status, order);
		this.deadlineDate = deadlineDate;
		this.paymentDate = paymentDate;
	}
	public Date getDeadlineDate() {
		return deadlineDate;
	}
	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
	

}
