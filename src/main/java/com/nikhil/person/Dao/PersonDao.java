package com.nikhil.person.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nikhil.person.entity.Person;

@Repository
public interface PersonDao extends CrudRepository<Person, Integer>{
	

}
