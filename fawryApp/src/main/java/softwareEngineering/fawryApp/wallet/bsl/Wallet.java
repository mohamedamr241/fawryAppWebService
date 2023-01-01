package softwareEngineering.fawryApp.wallet.bsl;

import softwareEngineering.fawryApp.payment.bsl.Payment;
import softwareEngineering.fawryApp.user.models.Account;
import softwareEngineering.fawryApp.user.models.User;

public class Wallet implements Payment{ 
	private double previousBalance = 0, balance = 0;
	
	public double pay(double amount) {
		balance-=amount;
		return  amount;
	}
	
	public String chargeViaCreditCard(double balance)
	{
		previousBalance = this.balance;
		this.balance += balance;
		return "Your wallet balance is updated from " + previousBalance + " $ to " + this.balance + " $ successfully";
	}
	
	public double getPreviousBalance()
	{
		return previousBalance;
	}
	
	public double getBalance()
	{
		return balance;
	}

	
	public static Wallet getUserWallet(String email) {
		Account acc = User.getAccByEmail(email);
		return (acc == null)? null : acc.getWallet();
	}
	
}
