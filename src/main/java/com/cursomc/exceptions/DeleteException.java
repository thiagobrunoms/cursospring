package com.cursomc.exceptions;

public class DeleteException extends RuntimeException {
	
	public DeleteException() {
		super("Delete was not possible!");
	}
	
	
	public DeleteException(String msg) {
		super(msg);
	}
	
	

}
