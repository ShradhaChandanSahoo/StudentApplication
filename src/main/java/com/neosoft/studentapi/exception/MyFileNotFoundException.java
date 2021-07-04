package com.neosoft.studentapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MyFileNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	private String message;

}
