package com.nikhil.person.exceptionHandler;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Component;

@Component
public class PersonNotFound extends NotFoundException {

	// can leave empty or add method
	public String getMessage() {
		return "Person not found.";
	}

}
