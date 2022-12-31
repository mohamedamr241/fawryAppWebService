
package softwareEngineering.fawryApp.bsl;
import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.models.Account;
import softwareEngineering.fawryApp.models.Refund;
import softwareEngineering.fawryApp.models.TransactionEntity;
import softwareEngineering.fawryApp.models.Transactions;
import softwareEngineering.fawryApp.models.User;

@Service
public class RefundBsl{
	
	public Refund refund = new Refund();
	
	public String processRefund(int transId)
	{
		int index = 0;
		boolean notFound = false;
		for(int i = 0; i < Transactions.getTransactions().size(); i++)
		{
			TransactionEntity t = Transactions.getTransactions().get(i);
			if(t.transId == transId)
			{
				for(TransactionEntity tt: refund.getrefundRequestList())
				{
					if(tt.transId == transId)
					{
						index = refund.getrefundRequestList().indexOf(tt);
						break;
					}
				}
	
				TransactionEntity rq = refund.getrefundRequestList().get(index);
				if(t.amount == rq.amount && t.serviceName.equals(rq.serviceName) && t.email.equals(rq.email))
					return "Refund request with tansaction id " + transId + " is correct";
				else notFound = true;
			}
		}
		if(notFound) return "Refund request with tansaction id " + transId + " is incorrect";
		return "There's no transaction with id " + transId;
	}
	
	public String requestRefund(TransactionEntity t) 
	{
		for(TransactionEntity obj : refund.getrefundRequestList())
		{
			if(obj.transId == t.transId)
				return "this refund request already exsist";
		}
		refund.addRefundRequest(t);
		return "Your request refund is submited, it will be processed and you'll get notification";
	}
	
	public String accOrRej(String decision, int transactionId)//
	{
	
		int index = 0;
		String returnMesg = null;
		for(TransactionEntity t: refund.getrefundRequestList())
		{
			if(transactionId == t.transId)
			{
				index = refund.getrefundRequestList().indexOf(t);
				break;
			}
		}
		TransactionEntity rq = refund.getrefundRequestList().get(index);
		if(decision.equals("accept"))
		{
			Wallet userWallet = Wallet.getUserWallet(refund.getrefundRequestList().get(index).email);
			double balance = userWallet.getBalance();
			userWallet.chargeViaCreditCard(rq.amount);
			notifyRefund(rq.email,"your refund request (" + rq.transId + ", " + rq.serviceName + ", " + rq.amount + ") is accepted and your wallet balance is updated from " + balance + " $ to " + userWallet.getBalance() + " $");
			returnMesg =  "Refund request with Id " + transactionId + " is accepted and user's wallet balance is updated from " + balance + " $ to " + userWallet.getBalance() + " $";
			for(int i = 0; i < Transactions.getTransactions().size(); i++)
			{
				TransactionEntity t = Transactions.getTransactions().get(i);
				if(t.transId == transactionId)
				{
					Transactions.getTransactions().remove(t);
				}
			}
		}
		else {
			notifyRefund(rq.email,"your refund request (" + rq.transId + ", " + rq.serviceName + ", " + rq.amount + ") is rejected"); 
			returnMesg = "Refund request with Id " + transactionId + " is rejected.";
		}
		
		refund.removeRefundRequest(index);
		
		return returnMesg;
	}
	
	public void notifyRefund(String email,String message) {
		Account acc = User.getAccByEmail(email);
		acc.notifications.add(message);
	}
}