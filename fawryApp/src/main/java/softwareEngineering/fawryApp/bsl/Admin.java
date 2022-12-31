package softwareEngineering.fawryApp.bsl;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.yaml.snakeyaml.util.ArrayUtils;

import softwareEngineering.fawryApp.models.Account;
import softwareEngineering.fawryApp.models.TransactionEntity;
import softwareEngineering.fawryApp.models.Transactions;
@Component
public class Admin{
	private static ArrayList<TransactionEntity> refundReqList = new ArrayList<TransactionEntity>();
	
	public void setrefundRequest(TransactionEntity t) {
		refundReqList.add(t);
	}

	
	public ArrayList<TransactionEntity> getrefundRequestList() {
		return refundReqList;
	}	
	
	public String signIn(String email, String pass)
	{
		if(Account.adminEmail.equals(email)&& Account.adminPass.equals(pass))
			return "logged in successfully";
		return "Email or password incorrent";
	}
	
	public void addDiscount(String service, int dis)
	{
		SpecificDiscount.serviceDiscount.put(service,dis);
	}

	public String processRefund(int transId)//??
	{
		int index = 0;
		boolean notFound = false;
		for(int i = 0; i < Transactions.transactions.size(); i++)
		{
			TransactionEntity t = Transactions.transactions.get(i);
			if(t.transId == transId)
			{
				for(TransactionEntity tt: refundReqList)
				{
					if(tt.transId == transId)
					{
						index = refundReqList.indexOf(tt);
						break;
					}
				}
	
				TransactionEntity rq = refundReqList.get(index);
				if(t.amount == rq.amount && t.serviceName.equals(rq.serviceName) && t.email.equals(rq.email))
					return "Refund request with tansaction id " + transId + " is correct";
				else notFound = true;
			}
		}
		if(notFound) return "Refund request with tansaction id " + transId + " is incorrect";
		return "There's no transaction with id " + transId;
	}
	
	
	public String accOrRej(String decision, int transactionId)
	{
		int index = 0;
		String returnMesg = null;
		for(TransactionEntity t: refundReqList)
		{
			if(transactionId == t.transId)
			{
				index = refundReqList.indexOf(t);
				break;
			}
		}
		TransactionEntity rq = refundReqList.get(index);
		if(decision.equals("accept"))
		{
			Wallet userWallet = Wallet.getUserWallet(refundReqList.get(index).email);
			double balance = userWallet.getBalance();
			userWallet.chargeViaCreditCard(rq.amount);
			NotifyRefund(rq.email,"your refund request (" + rq.transId + ", " + rq.serviceName + ", " + rq.amount + ") is accepted and your wallet balance is updated from " + balance + " $ to " + userWallet.getBalance() + " $");
			returnMesg =  "Refund request with Id " + transactionId + " is accepted and user's wallet balance is updated from " + balance + " $ to " + userWallet.getBalance() + " $";
			for(int i = 0; i < Transactions.transactions.size(); i++)
			{
				TransactionEntity t = Transactions.transactions.get(i);
				if(t.transId == transactionId)
				{
					Transactions.transactions.remove(t);
				}
			}
		}
		else {
			NotifyRefund(rq.email,"your refund request (" + rq.transId + ", " + rq.serviceName + ", " + rq.amount + ") is rejected"); 
			returnMesg = "Refund request with Id " + transactionId + " is rejected.";
		}
		refundReqList.remove(index);
		return returnMesg;
	}

	
	
	public static void NotifyRefund(String email,String message) {
		for(Map.Entry<String, User> entry : Account.users.entrySet())
		{
			if(entry.getKey().equals(email)) {
				entry.getValue().notifications.add(message);
			}
		}
	}

	
}
