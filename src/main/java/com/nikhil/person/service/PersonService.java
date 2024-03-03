package com.nikhil.person.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nikhil.person.Dao.PersonDao;
import com.nikhil.person.entity.Person;

@Service
public class PersonService {

	@Autowired
	private PersonDao personDao;

	// add person
	public Person addperson(Person person) {
		return personDao.save(person);

	}

	// get Person by id
	public Person getPerson(int id) {
		Person person = personDao.findById(id).get();
		System.out.println(person);
		if (person != null) {
			return person;
		} else {
			return null;
		}
	}
	
	//update
	
	//delete
	public boolean removePerson(int id) {
		Person person = personDao.findById(id).get();
		if (person != null) {
			personDao.delete(person);
			return true;
		} else {
			return false;
		}
	}
	

}
