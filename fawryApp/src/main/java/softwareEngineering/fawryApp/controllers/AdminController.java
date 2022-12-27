package softwareEngineering.fawryApp.controllers;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.bsl.Admin;
import softwareEngineering.fawryApp.models.Account;
@RestController
public class AdminController{
	private Admin admin = new Admin();
	@RequestMapping(value="/admin/signIn",method = RequestMethod.POST)
	public String signIn(@RequestBody Account acc) {
		return admin.signIn(acc.email, acc.password);
	}
	
}

