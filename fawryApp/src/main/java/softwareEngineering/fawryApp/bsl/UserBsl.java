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
			if(a.email.equals(acc.email) && a.password.equals(acc.password)) {
				if(a.timeStamp.equals("0")) {
					String timeStamp=TimeStampBsl.timeStampCreation();
					a.timeStamp=timeStamp;
					return "your timeStamp is "+timeStamp;					
				}
				else {
					return "you already signedIn";
				}
			}
		}
		return "Please enter correct username and password";
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
	public String signOut(String timeStamp) {
		for(Account acc: User.getAccounts()) {
			if(acc.timeStamp.equals(timeStamp)) {
				acc.timeStamp="0";
				return "logged out successfully";
			}
		}
		return "logging out failed";
	}
	public static void notify(String message)
	{
		for(Account acc : User.getAccounts())
		{
			acc.notifications.add(message);
		}
	}


	



}