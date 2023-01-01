
package softwareEngineering.fawryApp.refund.bsl;
import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.refund.models.Refund;
import softwareEngineering.fawryApp.user.models.Account;
import softwareEngineering.fawryApp.transaction.models.TransactionEntity;
import softwareEngineering.fawryApp.transaction.models.Transactions;
import softwareEngineering.fawryApp.user.models.User;
import softwareEngineering.fawryApp.wallet.bsl.Wallet;

@Service
public class RefundBsl{
	
	public Refund refund = new Refund();
	
	public String processRefund(int transId)
	{
		int index = 0;
		boolean notFound = false;
		for(TransactionEntity t : Transactions.getTransactions())
		{
			if(t.getTransId() == transId)
			{
				for(TransactionEntity tt: refund.getrefundRequestList())
				{
					if(tt.getTransId() == transId)
					{
						index = refund.getrefundRequestList().indexOf(tt);
						break;
					}
				}
	
				TransactionEntity rq = refund.getrefundRequestList().get(index);
				if(t.getAmount() == rq.getAmount() && t.getServiceName().equals(rq.getServiceName()) && t.getEmail().equals(rq.getEmail()))
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
			if(obj.getTransId() == t.getTransId())
				return "Refund request with transactin id " + t.getTransId() + " already exsist";
			else found = false;	
		}
		if(!found)
			return "There's no transaction with id " + t.getTransId();
		refund.addRefundRequest(t);
		return "Your request refund is submited, it will be processed and you'll get notification";
	}
	
	public String accOrRej(String decision, int transactionId)
	{
	
		int index = 0;
		String returnMesg = null;
		for(TransactionEntity t: refund.getrefundRequestList())
		{
			if(transactionId == t.getTransId())
			{
				index = refund.getrefundRequestList().indexOf(t);
				break;
			}
		}
		TransactionEntity rq = refund.getrefundRequestList().get(index);
		if(decision.equals("accept") || decision.equals("1"))
		{
			Wallet userWallet = Wallet.getUserWallet(refund.getrefundRequestList().get(index).getEmail());
			userWallet.chargeViaCreditCard(rq.getAmount());
			Account acc = User.getAccByEmail(rq.getEmail());
			acc.addNotification("your refund request with transaction id " + transactionId  + " is accepted and your wallet balance is updated from " + userWallet.getPreviousBalance() + " $ to " + userWallet.getBalance() + " $");
			returnMesg =  "Refund request with Id " + transactionId + " is accepted and user's wallet balance is updated from " + userWallet.getPreviousBalance() + " $ to " + userWallet.getBalance() + " $";
			
			for(int i = 0; i < Transactions.getTransactions().size(); i++)
			{
				TransactionEntity t = Transactions.getTransactions().get(i);
				if(t.getTransId() == transactionId)
					Transactions.getTransactions().remove(t);
			}
		}
		else if(decision.equals("reject") || decision.equals("0")){
			Account acc = User.getAccByEmail(rq.getEmail());
			acc.addNotification("your refund request with transaction id " + transactionId  + " is rejected");
			returnMesg = "Refund request with Id " + transactionId + " is rejected.";
		}
		
		refund.removeRefundRequest(index);
		return returnMesg;
	}
}