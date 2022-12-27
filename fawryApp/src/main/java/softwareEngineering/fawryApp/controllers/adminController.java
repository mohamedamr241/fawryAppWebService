package softwareEngineering.fawryApp.controllers;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.bsl.Admin;
import softwareEngineering.fawryApp.bsl.User;
import softwareEngineering.fawryApp.bsl.personbsl;
import softwareEngineering.fawryApp.models.Account;
import softwareEngineering.fawryApp.models.Person;
@RestController
public class adminController{
	private Admin admin = new Admin();
	@RequestMapping(value="/admin/signIn",method = RequestMethod.POST)
	public String signIn(@RequestBody Account acc) {
		return admin.signIn(acc.email, acc.password);
	}
	
}

