package com.neosoft.studentapi.handler;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.neosoft.studentapi.model.ErrorResponse;


@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<Object> handleFileNotFoundException(FileNotFoundException exc) {
        
        List<String> details = new ArrayList<String>();
        details.add(exc.getMessage());
        ErrorResponse err = new ErrorResponse(LocalDateTime.now(), "File Not Found" ,details);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
	
	
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Object> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        
        List<String> details = new ArrayList<String>();
        details.add(exc.getMessage());
        ErrorResponse err = new ErrorResponse(LocalDateTime.now(), "File Size Exceeded" ,details);
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(err);
    }
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
		
		List<String> details = new ArrayList<String>();
        details.add(ex.getMessage());
        ErrorResponse err = new ErrorResponse(LocalDateTime.now(), "File Not Found" ,details);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
	}

}
