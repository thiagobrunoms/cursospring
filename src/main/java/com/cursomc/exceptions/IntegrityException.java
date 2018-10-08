package com.cursomc.exceptions;

public class IntegrityException extends RuntimeException {
	
	public IntegrityException() {
		super("An integrity exception has been thrown. Check your data relationship!");
	}
	
	
	public IntegrityException(String msg) {
		super(msg);
	}
	
	

}
