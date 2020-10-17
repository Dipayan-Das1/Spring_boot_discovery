package spring.edu.ms.app.personservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import spring.edu.ms.app.personservice.dto.MessageDto;
import spring.edu.ms.app.personservice.exception.PersonNotFoundException;

@ControllerAdvice
@Component
public class PersonControllerAdvice {

	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<MessageDto> handleException(IllegalArgumentException iae) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(iae.getMessage()));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PersonNotFoundException.class)
	public ResponseEntity<MessageDto> handleException(PersonNotFoundException pnfe) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageDto(pnfe.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MessageDto> handleException(RuntimeException ex) {
		ex.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageDto(ex.getMessage()));
	}
}
