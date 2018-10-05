package com.cursomc.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	
	public ObjectNotFoundException() {
		super("Object not found!");
	}
	
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	

}
