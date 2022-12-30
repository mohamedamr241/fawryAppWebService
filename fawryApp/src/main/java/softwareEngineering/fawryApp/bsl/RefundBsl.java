
package softwareEngineering.fawryApp.bsl;
import softwareEngineering.fawryApp.models.Refund;
import softwareEngineering.fawryApp.models.TransactionEntity;
import softwareEngineering.fawryApp.models.Transactions;

public class RefundBsl{
	
	public String processRefund(int transId)
	{
		int index = 0;
		boolean notFound = false;
		for(int i = 0; i < Transactions.getTransactions().size(); i++)
		{
			TransactionEntity t = Transactions.getTransactions().get(i);
			if(t.transId == transId)
			{
				for(TransactionEntity tt: Refund.getrefundRequestList())
				{
					if(tt.transId == transId)
					{
						index = Refund.getrefundRequestList().indexOf(tt);
						break;
					}
				}
	
				TransactionEntity rq = Refund.getrefundRequestList().get(index);
				if(t.amount == rq.amount && t.serviceName.equals(rq.serviceName) && t.userEmail.equals(rq.userEmail))
					return "Refund request with tansaction id " + transId + " is correct";
				else notFound = true;
			}
		}
		if(notFound) return "Refund request with tansaction id " + transId + " is incorrect";
		return "There's no transaction with id " + transId;
	}
	
}