package softwareEngineering.fawryApp.bsl;

import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.models.Account;
import softwareEngineering.fawryApp.models.User;

@Service
public class UserBsl{
	
	public User user = new User();
	
	public boolean signIn(Account acc)
	{
		for (Account a : User.getAccounts())
		{
			if(a.getEmail().equals(acc.getEmail()) && a.getPassword().equals(acc.getPassword()))
				return true;
		}
		return false;
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
	
	public static void notify(String message)
	{
		for(Account acc : User.getAccounts())
		{
			acc.addNotification(message);
		}
	}


	



}