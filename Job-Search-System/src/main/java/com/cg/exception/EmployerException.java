package com.cg.exception;

public class EmployerException extends Exception {
private static final long serialVersionUID = 1L;
	
	private String message;

	public EmployerException() {
		
	}

	public EmployerException(String message) {
		super(message);
		this.message=message;
	}
	
	public EmployerException(String message,Exception e) {
		super(message,e);
		this.message=message;
	}

	@Override
	public String toString() {
		return "EmployerException [message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}
	
}
