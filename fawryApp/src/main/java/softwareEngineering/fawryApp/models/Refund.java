package softwareEngineering.fawryApp.models;

import java.util.ArrayList;

public class Refund{
	private static ArrayList<TransactionEntity> refundReqList = new ArrayList<TransactionEntity>();
	private static ArrayList<TransactionEntity> refundedTransactions = new ArrayList<TransactionEntity>();
	
	
	public void addRefundRequest(TransactionEntity rq)
	{
		for(TransactionEntity t : Transactions.getTransactions())
		{
			if(t.getTransId() == rq.getTransId())
			{
				refundedTransactions.add(t);	
				refundReqList.add(rq);	
				break;
			}
		}
	}
	
	public void removeRefundRequest(int ind)
	{
		refundReqList.remove(ind);
	}
	
	public ArrayList<TransactionEntity> getrefundRequestList() {
		return refundReqList;
	}	
	
	public ArrayList<TransactionEntity> getrefundedTransactions() {
		return refundedTransactions;
	}	


}