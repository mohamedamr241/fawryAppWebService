package softwareEngineering.fawryApp.controllers;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.bsl.Admin;
import softwareEngineering.fawryApp.bsl.User;
import softwareEngineering.fawryApp.models.Account;
@RestController
public class userController{
	private User newUser = new User();	
	private Admin admin = new Admin();
	@RequestMapping(value="/user/signUp",method = RequestMethod.POST)
	public String signUp(@RequestBody Account acc) {
		String res=newUser.signUp(acc.userName, acc.email, acc.password, newUser);
		return res;
	}
	
	@RequestMapping(value="/user/signIn",method = RequestMethod.POST)
	public String signIn(@RequestBody Account acc) {
		return newUser.signIn(acc.email, acc.password);
	}
	
	@RequestMapping(value="/user/notifications/{email}",method = RequestMethod.GET)
	public ArrayList<String> userNotifications(@PathVariable("email") String email){
		System.out.println(Account.users.size());
		for (Map.Entry<String, User> entry : Account.users.entrySet()) {
			if (entry.getKey().equals(email)) {
				return entry.getValue().notifications;
			}
		}
		return new ArrayList<String>(Arrays.asList("This user dosen't exist, signup please"));
	}
	
}

