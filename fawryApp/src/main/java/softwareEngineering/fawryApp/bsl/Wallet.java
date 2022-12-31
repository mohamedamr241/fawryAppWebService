package softwareEngineering.fawryApp.bsl;

import softwareEngineering.fawryApp.models.Account;
import softwareEngineering.fawryApp.models.User;

public class Wallet implements Payment{ 
	private double previousBalance = 0, balance = 0;
	
	public double pay(double amount) {
		balance-=amount;
		return  amount;
	}
	
	public String chargeViaCreditCard(double balance)
	{
		String ret = "Your wallet balance is updated from " + previousBalance;
		previousBalance = balance;
		this.balance += balance;
		return ret + " $ to " + this.balance + " $ successfully.";
	}
	
	public double getBalance()
	{
		return balance;
	}

	
	public static Wallet getUserWallet(String email) {
		for (Account ac : User.getAccounts())
		{
			if(ac.email.equals(email))
				return ac.wallet;
		}
		return null;
	}
	
}
