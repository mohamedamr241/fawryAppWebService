
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
				notFound = true;
			}
		}
		if(notFound) return "Refund request with tansaction id " + transId + " is incorrect";
		return "There's no transaction with id " + transId;
	}
	
	public String requestRefund(TransactionEntity t) 
	{
		boolean found = true;
		for(TransactionEntity obj : refund.getrefundRequestList())
		{
			if(obj.transId == t.transId)
				return "Refund request with transactin id " + t.transId + " already exsist";
			else found = false;	
		}
		if(!found)
			return "There's no transaction with id " + t.transId;
		refund.addRefundRequest(t);
		return "Your request refund is submited, it will be processed and you'll get notification";
	}
	
	public String accOrRej(String decision, int transactionId)
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
		if(decision.equals("accept") || decision.equals("1"))
		{
			Wallet userWallet = Wallet.getUserWallet(refund.getrefundRequestList().get(index).email);
			double balance = userWallet.getBalance();
			userWallet.chargeViaCreditCard(rq.amount);
			Account acc = User.getAccByEmail(rq.email);
			acc.notifications.add("your refund request whith transaction id " + transactionId  + " is accepted and your wallet balance is updated from " + balance + " $ to " + userWallet.getBalance() + " $");
			returnMesg =  "Refund request with Id " + transactionId + " is accepted and user's wallet balance is updated from " + balance + " $ to " + userWallet.getBalance() + " $";
			
			for(int i = 0; i < Transactions.getTransactions().size(); i++)
			{
				TransactionEntity t = Transactions.getTransactions().get(i);
				if(t.transId == transactionId)
					Transactions.getTransactions().remove(t);
			}
		}
		else if(decision.equals("reject") || decision.equals("0")){
			Account acc = User.getAccByEmail(rq.email);
			acc.notifications.add("your refund request whith transaction id " + transactionId  + " is rejected");
			returnMesg = "Refund request with Id " + transactionId + " is rejected.";
		}
		
		refund.removeRefundRequest(index);
		return returnMesg;
	}
}