package softwareEngineering.fawryApp.bsl;
import java.util.*;

import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.models.Account;
import softwareEngineering.fawryApp.models.TransactionEntity;
import softwareEngineering.fawryApp.models.Transactions;

@Service
public class User implements Observer{
	private Admin admin = new Admin();
	public ArrayList<String> notifications = new ArrayList<String>();
	public String signIn(String email, String password)
	{
		
		for (Map.Entry<String, String> entry : Account.userAccounts.entrySet())
		{
			if(entry.getKey().equals(email) && entry.getValue().equals(password))
				return "logged in successfully";
		}
		return "Email or password incorrent";
	}
	
	public String signUp(String userName, String email, String password, User u)
	{
		
		for (String e : Account.userAccounts.keySet())
		{
			if(e.equals(email))
				return "Email already exists";
		}
		Account.users.put(email,u);
		Transactions.userTransactionNumber.put(email,0);
		Account.userAccounts.put(email, password);
		admin.subscribe(u);
		Account.userWallet.put(email, new Wallet());
		return "Account created successfully";
	}
	
	public String requestRefund(TransactionEntity t) 
	{
		admin.setrefundRequest(t);
		return "Your request refund is submited, it will be processed and you'll get notification";
	}

	@Override
	public void update(String note) {
		notifications.add(note);
	}
}



















