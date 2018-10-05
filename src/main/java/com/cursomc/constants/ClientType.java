package com.cursomc.constants;

public enum ClientType {
	
	F_PERSON(1, "Pessoa Física"),
	J_PERSON(2, "Pessoa Jurídica");
	
	private Integer type;
	private String name;
	
	private ClientType(Integer type, String name) {
		this.type = type;
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static ClientType toEnum(Integer type) throws Exception {
		if (type == null) return null;
		
		for (ClientType clientType : ClientType.values()) {
			if (clientType.getType().intValue() == type.intValue()) 
				return clientType;
		}
		
		throw new Exception("ClientType does not exist!");
	}
	

}
