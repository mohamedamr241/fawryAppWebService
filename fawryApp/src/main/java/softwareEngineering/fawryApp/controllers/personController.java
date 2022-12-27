package softwareEngineering.fawryApp.controllers;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.bsl.personbsl;
import softwareEngineering.fawryApp.models.Person;
@RestController
public class personController{
	private personbsl personbb;
	public personController(personbsl p) {
		personbb=p;
	}
	@RequestMapping(value="/persons",method = RequestMethod.POST)
	public String addPerson(@RequestBody Person p) {
		return personbb.add(p);
	}
	
}

