package com.fan.estore.myexception;

public class BookException extends Exception {
	private static final long serialVersionUID = 1L;

	public BookException() {
		super();
	}

	public BookException(String message, Throwable cause) {
		super(message, cause);
	}

	public BookException(String message) {
		super(message);
	}

	public BookException(Throwable cause) {
		super(cause);
	}
	
}
