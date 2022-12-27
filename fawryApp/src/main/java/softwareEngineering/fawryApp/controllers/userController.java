package softwareEngineering.fawryApp.controllers;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.bsl.User;
import softwareEngineering.fawryApp.models.Account;
@RestController
public class userController{
	User newUser = new User();
	@RequestMapping(value="/user/signUp",method = RequestMethod.POST)
	public String signUp(@RequestBody Account acc) {
		return newUser.signUp(acc.userName, acc.email, acc.password, newUser);
	}
	
	@RequestMapping(value="/user/signIn",method = RequestMethod.POST)
	public String signIn(@RequestBody Account acc) {
		return newUser.signIn(acc.email, acc.password);
	}
	
}

