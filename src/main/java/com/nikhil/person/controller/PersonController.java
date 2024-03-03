package com.nikhil.person.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nikhil.person.entity.Person;
import com.nikhil.person.exceptionHandler.PersonNotFound;
import com.nikhil.person.responsestructure.ResponseStructure;
import com.nikhil.person.service.PersonService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class PersonController {
	@Autowired
	private PersonService personService;

	@GetMapping("test")
	public String getMessage() {
		return "it is hitting...";
	}

	@Operation(description = "This is used to save the Person", summary = "Using Post method we can save Person")
	@ApiResponses(value = {
	    @ApiResponse(description = "Save the Person", responseCode = "200"),
	    @ApiResponse(description = "Unable to save", responseCode = "400")
	})
	@PostMapping("add")
	public ResponseEntity<ResponseStructure<Person>> addPerson(@RequestBody Person person) throws PersonNotFound {

		if (person != null && person.getGender() != null && person.getName() != null) {
			ResponseStructure<Person> response = new ResponseStructure<Person>();
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Person saved successfully...");
			response.setData(person);

			personService.addperson(person);
			return new ResponseEntity<ResponseStructure<Person>>(response, HttpStatus.CREATED);
		}

		// this exception in controller will be caught in MyapplicationExceptionHandler
		// annotated by @restcontroller
		throw new PersonNotFound();

	}

	// find by ID
	@GetMapping("person/{id}")
	public ResponseEntity<ResponseStructure<Person>> getPerson(@PathVariable int id) throws PersonNotFound {
		Person person = personService.getPerson(id);

		if (person != null) {
			ResponseStructure<Person> response = new ResponseStructure<Person>();
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage(HttpStatus.FOUND.getReasonPhrase());
			response.setData(person);
			return new ResponseEntity<ResponseStructure<Person>>(response, HttpStatus.FOUND);
		}
		throw new PersonNotFound();
	}

	// update
	@PutMapping("update")
	public ResponseEntity<ResponseStructure<Person>> updatePerson(@RequestBody Person person) throws PersonNotFound {

		if (person != null && person.getGender() != null && person.getName() != null) {
			ResponseStructure<Person> response = new ResponseStructure<Person>();
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Person updated successfully...");
			response.setData(person);

			personService.addperson(person);
			return new ResponseEntity<ResponseStructure<Person>>(response, HttpStatus.OK);
		}

		// this exception in controller will be caught in MyapplicationExceptionHandler
		// annotated by @restcontroller
		throw new PersonNotFound();

	}

	// Remove
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseStructure<Person>> removePerson(@PathVariable int id) throws PersonNotFound {

		ResponseStructure<Person> response = new ResponseStructure<Person>();
		response.setStatusCode(HttpStatus.NO_CONTENT.value());
		response.setMessage(HttpStatus.NO_CONTENT.getReasonPhrase());
		response.setData(personService.getPerson(id));
		
		boolean delete = personService.removePerson(id);
		return new ResponseEntity<ResponseStructure<Person>>(response, HttpStatus.NO_CONTENT);

	}
}
