package softwareEngineering.fawryApp.user.bsl;

import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.timeStampBsl.TimeStampBsl;
import softwareEngineering.fawryApp.user.models.Account;
import softwareEngineering.fawryApp.user.models.User;

@Service
public class UserBsl{
	
	public User user = new User();
	
	public String signIn(Account acc)
	{
		
		for (Account a : User.getAccounts())
		{
			if(a.getEmail().equals(acc.getEmail()) && a.getPassword().equals(acc.getPassword())) {
				if(a.gettimeStamp().equals("0")) {
					String timeStamp = TimeStampBsl.timeStampCreation();
					a.setTmieStamp(timeStamp);
					return "logged in successfully, " + "your time stamp: " + a.gettimeStamp();					
				}
				else {
					return "you already signedIn";
				}
			}
		}
		return "Please enter correct username and password";
	}
	

	public boolean signUp(Account acc)
	{
		for (Account a : User.getAccounts())
		{
			if(a.getUsername().equals(acc.getUsername()) || a.getEmail().equals(acc.getEmail()))
				return false;
		}
		
		acc.createWallet();
		user.addAccount(acc);
		return true;
	}
	
	
	public String signOut(String timeStamp) {
		for(Account acc: User.getAccounts()) {
			if(acc.gettimeStamp().equals(timeStamp)) {
				acc.setTmieStamp("0");;
				return "logged out successfully";
			}
		}
		return "logging out failed";
	}

	public static void notify(String message)
	{
		for(Account acc : User.getAccounts())
		{
			acc.addNotification(message);
		}
	}


	



}