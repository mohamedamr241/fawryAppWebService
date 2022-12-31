package softwareEngineering.fawryApp.bsl;

import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.models.Account;
import softwareEngineering.fawryApp.models.User;

@Service
public class UserBsl{
	
	public User user = new User();
	
	public String signIn(Account acc)
	{
		
		for (Account a : User.getAccounts())
		{
			if(a.email.equals(acc.email) && a.password.equals(acc.password))
				return "logged in successfully";
		}
		return "Email or password incorrent";
	}
	
	public String signUp(Account acc)
	{
		
		for (Account a : User.getAccounts())
		{
			if(a.username.equals(acc.username) || a.email.equals(acc.email))
				return "Email already exists";
		}
		acc.wallet = new Wallet();
		user.addAccount(acc);
		return "Account created successfully";
	}
	
	public static void notify(String message)
	{
		for(Account acc : User.getAccounts())
		{
			acc.notifications.add(message);
		}
	}


	



}