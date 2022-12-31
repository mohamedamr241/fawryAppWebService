package softwareEngineering.fawryApp.bsl;

import java.util.*;

import softwareEngineering.fawryApp.models.Account;
import softwareEngineering.fawryApp.models.TransactionEntity;
import softwareEngineering.fawryApp.models.Transactions;
import softwareEngineering.fawryApp.models.User;

public class PaymentBsl{
	public String email, CCN, creditCardNum, mobileNumber;
	public double amount;
	//String timeStamp;
	//validate data
	
	double disPrice;
	static int transactionCnt = 0;
	Map<String, String> transactionDetails = new HashMap<String, String>();
	
	public Map<String, String> pay(Services service, String serviceName, String serviceProvider, String paymentMethod) {
		Account userAcc = User.getAccByEmail(email);
		
		
	if(paymentMethod.equals("creditCard") || paymentMethod.equals("cash"))
	{
		transactionDetails.put("Service name ", serviceName);
		transactionDetails.put("Service provider name ", serviceProvider);
		Payment payMethod = null;
		if(paymentMethod.equals("creditCard"))
			payMethod = new CreditCard();
		
		else if(paymentMethod.equals("cash"))
			payMethod = new Cash();
		
		 if(userAcc.isFirstTrans) {
			payMethod = new OverallDiscount(payMethod);
			transactionDetails.put("Overall discount: ",  " 10% off applied (as it's your first transaction)");
		}
		
		int discountVal = SpecificDiscount.checkDiscount(serviceName);
		if(discountVal > 0)
		{
			payMethod = new SpecificDiscount(payMethod);
			transactionDetails.put("Specific discount: ", discountVal + " % off applied");
		}
		
		service.setPayMethod(payMethod);	
		disPrice = service.performPayMethod(amount);
		transactionDetails.put("Price after discount ", disPrice + " $");
		transactionDetails.put("Payment with is done via ", paymentMethod);
		userAcc.isFirstTrans = false;
		TransactionEntity t = new TransactionEntity(++transactionCnt, disPrice, serviceProvider ,serviceName, email, paymentMethod);
		Transactions.addTransaction(t);
		transactionDetails.put("Transaction ID: ", transactionCnt + " (must be known so you can request rufund)");
		
	}
	
	
	else if (paymentMethod.equals("wallet")){ 
		Wallet userWallet = Wallet.getUserWallet(email);
		if(userAcc.isFirstTrans) {
			Payment discount = new OverallDiscount(userWallet);
			
			int discountVal = SpecificDiscount.checkDiscount(serviceName);
			if(discountVal > 0)
			{
				discount = new SpecificDiscount(discount);
				transactionDetails.put("Specific discount: ", discountVal + " % off applied");
			}
			service.setPayMethod(discount);
		}
		else {
			
			int discountVal = SpecificDiscount.checkDiscount(serviceName);
			if(discountVal > 0)
			{
				Payment discount = new SpecificDiscount(userWallet);
				service.setPayMethod(discount);
				transactionDetails.put("Specific discount: ", discountVal + " % off applied");
			}
			else {
				service.setPayMethod(userWallet);								
			}
		}
		if(userWallet.getBalance() >= amount) {
			transactionDetails.put("Service name ", serviceName);
			transactionDetails.put("Service provider name ", serviceProvider);
			if(userAcc.isFirstTrans)
				transactionDetails.put("Overall discount: ",  " 10% off applied (as it's your first transaction)");
			disPrice = service.performPayMethod(amount);
			transactionDetails.put("Price after discount: ", disPrice + " $");
			transactionDetails.put("Payment with is done via ", paymentMethod);
			transactionDetails.put("walletBalance: ", userWallet.getBalance() + " $");
			userAcc.isFirstTrans = false;
			TransactionEntity t = new TransactionEntity(++transactionCnt, disPrice, serviceProvider ,serviceName, email, paymentMethod);
			Transactions.addTransaction(t);
			transactionDetails.put("Transaction ID: ", transactionCnt + " (must be known so you can request rufund)");
		}
		else 
			transactionDetails.put("Your wallet balance is ", userWallet.getBalance() + " $ which is not enough to complete this transaction." );
	}
	return transactionDetails;
}
}