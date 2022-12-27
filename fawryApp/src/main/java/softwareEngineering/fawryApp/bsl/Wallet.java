package softwareEngineering.fawryApp.bsl;

import java.util.Map;

import softwareEngineering.fawryApp.models.Account;

public class Wallet implements Payment{ 
	
	private double balance = 0;
	public double pay(double amount) {
		balance-=amount;
		return  amount;
	}
	
	public String chargeViaCreditCard(double balance)
	{
		this.balance += balance;
		return "wallet is charged with " + balance +" successfully.";
	}
	
	public double getBalance()
	{
		return balance;
	}
	public static Wallet getUserWallet(String email) {
		for (Map.Entry<String, Wallet> entry : Account.userWallet.entrySet())
		{
			if(entry.getKey().equals(email))
				return entry.getValue();
		}
		return null;
	}
	
}
