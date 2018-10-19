package com.cursomc.constants;

public enum UserProfile {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENT(2, "ROLE_CLIENT");
	
	private Integer type;
	private String name;
	
	private UserProfile(Integer type, String name) {
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
	
	public static UserProfile toEnum(Integer type) {
		if (type == null) return null;
		
		for (UserProfile clientType : UserProfile.values()) {
			if (clientType.getType().intValue() == type.intValue()) 
				return clientType;
		}
		
		return null;
	}
	

}
