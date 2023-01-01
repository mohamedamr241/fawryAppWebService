package softwareEngineering.fawryApp.controllers;
import java.util.*;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.models.User;
import softwareEngineering.fawryApp.bsl.TimeStampBsl;
import softwareEngineering.fawryApp.bsl.UserBsl;
import softwareEngineering.fawryApp.models.Account;

@RestController
public class UserController{
	private UserBsl userbsl;
	
	public UserController(UserBsl userbsl)
	{
		this.userbsl = userbsl;
	}
	
	@RequestMapping(value="/user/signUp",method = RequestMethod.POST)
	public String signUp(@RequestBody Account acc) {
		return userbsl.signUp(acc);
	}
	
	@RequestMapping(value="/user/signIn",method = RequestMethod.POST)
	public String signIn(@RequestBody Account acc) {
		return userbsl.signIn(acc);
		
	}
	@RequestMapping(value="/user/signOut",method = RequestMethod.POST)
	public String signOut(@RequestBody Account acc) {
		return userbsl.signOut(acc.timeStamp);
		
	}
	@RequestMapping(value="/user/notifications/{email}",method = RequestMethod.GET)
	public ArrayList<String> Notifications(@RequestBody Account account){
		if(TimeStampBsl.checkValidation(account.timeStamp,account.email)) {
			for(Account acc : User.getAccounts())
			{
				if(acc.email.equals(account.email))
					return acc.notifications;
			}
			return new ArrayList<String>(Arrays.asList("Account doesn't exist"));			
		}
		return new ArrayList<String>(Arrays.asList("you must signIn first"));
		}
}

