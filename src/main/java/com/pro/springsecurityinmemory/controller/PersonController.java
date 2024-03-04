package com.pro.springsecurityinmemory.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pro.springsecurityinmemory.dto.Person;

@RestController
public class PersonController {
   
	List<Person> list=null;
	
	@GetMapping("/persons")
	public List<Person> getPerson(){
		
		Optional.ofNullable(list).ifPresentOrElse(list->{},
			()->{
		    list = new ArrayList<>();
			list.add(new Person(1, "Sumanth", "Gurramkonda"));
			list.add(new Person(2, "Sai", "p"));
			list.add(new Person(3, "Santhosh", "Guduri"));
		});
		return list;
	}
	
	@PostMapping("/person")
	public Person putPerson(@RequestBody Person person) {
		
		Optional.ofNullable(person).ifPresent((p)->{
			list.add(p);
		});
		return person;
	}
}














