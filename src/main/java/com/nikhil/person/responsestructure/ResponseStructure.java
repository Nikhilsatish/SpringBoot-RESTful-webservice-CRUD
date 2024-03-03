package com.nikhil.person.responsestructure;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStructure<T> {

	private int statusCode;
	private String message;
	private T data;
}
