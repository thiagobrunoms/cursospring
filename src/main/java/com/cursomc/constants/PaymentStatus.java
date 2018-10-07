package com.cursomc.constants;

public enum PaymentStatus {
	
	PENDING(1, "Pendente"),
	PAID(2, "Pago"),
	CANCELLED(3, "Cancelado");
	
	private Integer code;
	private String name;
	
	private PaymentStatus(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static PaymentStatus toEnum(Integer code) {
		if (code == null) return null;
		
		for (PaymentStatus status : PaymentStatus.values()) {
			if (status.getCode().intValue() == code.intValue())
				return status;
		}
		
		throw new IllegalArgumentException("StatusPayment with id " + code + " does not exist!");
			
	}
	
	
	

}
