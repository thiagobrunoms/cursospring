package com.cursomc.exceptions;

public class UserAlreadyExistException extends RuntimeException {
	
	public UserAlreadyExistException() {
		super("An integrity exception has been thrown. Check your data relationship!");
	}
	
	
	public UserAlreadyExistException(String msg) {
		super(msg);
	}
	
	

}
