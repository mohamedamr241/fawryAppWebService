package softwareEngineering.fawryApp.controllers;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> signUp(@RequestBody Account acc) {
		if(userbsl.signUp(acc)) 
			return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully");
		return ResponseEntity.ok("Email or username already exists");
	}
	
	@RequestMapping(value="/user/signIn",method = RequestMethod.POST)
	public ResponseEntity<String> signIn(@RequestBody Account acc) {
		return ResponseEntity.ok(userbsl.signIn(acc));
	}
	
	@RequestMapping(value="/user/signOut",method = RequestMethod.POST)
	public ResponseEntity<String> signOut(@RequestBody Account acc) {
		return ResponseEntity.ok(userbsl.signOut(acc.gettimeStamp()));
		
	}
	
	@RequestMapping(value="/user/notifications/{email}",method = RequestMethod.GET)
	public ResponseEntity<ArrayList<String>> Notifications(@PathVariable("email") String email){
		
		Account acc = User.getAccByEmail(email);
		if(acc == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<String>(Arrays.asList("Account Doesn't Exist")));
		if(TimeStampBsl.checkValidation(acc.gettimeStamp(), email)) 
			return ResponseEntity.ok(acc.getNotifications());
		return ResponseEntity.ok(new ArrayList<String>(Arrays.asList("You must signIn first")));
		}

}

