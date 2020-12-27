package com.cg.exception;

public class MessageException extends Exception{

	private static final long serialVersionUID = 1L;

	private String message;

	public MessageException() {

	}

	public MessageException(String message) {
		super(message);
		this.message = message;
	}
	public MessageException(String message, Exception e) {
		super(message, e);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "MessageException [message=" + message + "]";
	}


}
