package softwareEngineering.fawryApp.bsl;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.yaml.snakeyaml.util.ArrayUtils;

import softwareEngineering.fawryApp.models.Account;
import softwareEngineering.fawryApp.models.TransactionEntity;
import softwareEngineering.fawryApp.models.Transactions;
@Component
public class Admin implements Subject{
	private static ArrayList<TransactionEntity> refundReqList = new ArrayList<TransactionEntity>();
	static ArrayList<Observer> systemUsers = new ArrayList<Observer>();
	
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
		Notify(dis+"% discount is applied on "+ service);
	}

	public boolean processRefund(int transId)
	{
		for(int i = 0; i < Transactions.transactions.size(); i++)
		{
			TransactionEntity t = Transactions.transactions.get(i);
			if(t.transId == transId)
			{
				TransactionEntity rq = refundReqList.get(i);
				if(t.amount == rq.amount && t.serviceName.equals(rq.serviceName) && t.userEmail.equals(rq.userEmail))
					return true;
			}
			
		}
		return false;
	}
	
	
	public String accOrRej(String decision, int transactionId)
	{
		int index = 0;
		String returnMesg = null;
		if(decision.equals("accept"))
		{
			for(TransactionEntity t: refundReqList)
			{
				if(transactionId == t.transId)
				{
					index = refundReqList.indexOf(t);
					break;
				}
			}
			Wallet userWallet = Wallet.getUserWallet(refundReqList.get(index).userEmail);
			double balance = userWallet.getBalance();
			userWallet.chargeViaCreditCard(refundReqList.get(index).amount);
			NotifyRefund(refundReqList.get(index).userEmail,"your refund request is accepted and your wallet balance is updated from " + balance + " to " + userWallet.getBalance());
			returnMesg =  "Refund request with Id " + transactionId + " is accepted and user's wallet balance is updated from " + balance + "$ to " + userWallet.getBalance() + '$';
		}
		else {
			NotifyRefund(refundReqList.get(index).userEmail,"your refund request is rejected"); 
			returnMesg = "Refund request with Id " + transactionId + "is rejected.";
		}
		refundReqList.remove(index);
		//remove from transactions
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
	@Override
	public void Notify(String note) {
		for(int i=0;i<systemUsers.size();i++) {
			systemUsers.get(i).update(note);
		}
		
	}

	@Override
	public void subscribe(Observer ob) {
		systemUsers.add(ob);
	}
	
}
