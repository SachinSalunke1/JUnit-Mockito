package com.startech.mockito.annotation.exception_handling;

public class DatabaseReadException extends RuntimeException {

	public DatabaseReadException(String message) {
		super(message);
	}
}
