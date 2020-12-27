package com.cg.exception;
public class JobSeekerException extends Exception{
private static final long serialVersionUID = 1L;
	
	private String message;

	public JobSeekerException() {
		
	}

	public JobSeekerException(String message) {
		super(message);
		this.message = message;
	}
	public JobSeekerException(String message,Exception e) {
		super(message,e);
		this.message=message;
	}
	public String getMessage() {
		return message;
	}
	@Override
	public String toString() {
		return "JobSeekerException [message=" + message;
	}
}