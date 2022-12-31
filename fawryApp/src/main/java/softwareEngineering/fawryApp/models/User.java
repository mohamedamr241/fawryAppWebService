package softwareEngineering.fawryApp.models;

import java.util.*;

public class User{
	private static ArrayList<Account> accounts = new ArrayList<Account>();
	
	public static ArrayList<Account> getAccounts() {
		return accounts;
	}	
	
	public void addAccount(Account acc)
	{
		accounts.add(acc);
	}
	
	public static Account getAccByEmail(String email)
	{
		for(Account acc : accounts)
		{
			if(acc.email.equals(email))
				return acc;
		}
		return null;
	}



}



















