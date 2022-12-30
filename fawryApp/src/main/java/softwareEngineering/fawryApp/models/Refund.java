package softwareEngineering.fawryApp.models;

import java.util.ArrayList;

public class Refund{
	private static ArrayList<TransactionEntity> refundReqList = new ArrayList<TransactionEntity>();
	
	public static void setrefundRequest(TransactionEntity t) {
		refundReqList.add(t);
	}

	public static ArrayList<TransactionEntity> getrefundRequestList() {
		return refundReqList;
	}	
	
	public void addRefundRequest(TransactionEntity rq)
	{
		refundReqList.add(rq);
	}
	
	public void removeRefundRequest(int ind)
	{
		refundReqList.remove(ind);
	}

}