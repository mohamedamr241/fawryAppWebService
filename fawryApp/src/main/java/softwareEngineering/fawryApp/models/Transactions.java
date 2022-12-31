package softwareEngineering.fawryApp.models;

import java.util.*;

public class Transactions {
	private static ArrayList<TransactionEntity> transactions = new ArrayList<TransactionEntity>();
	public static void addTransaction(TransactionEntity obj)
	{
		transactions.add(obj);
	}
	
	public static ArrayList<TransactionEntity> getTransactions()
	{
		return transactions;
	}
	
	public static ArrayList<TransactionEntity> getWalletTransactions()
	{
		ArrayList<TransactionEntity> walletTrans = new ArrayList<TransactionEntity>();
		for(TransactionEntity t : transactions)
		{
			if(t.payMethod.equals("wallet"))
				walletTrans.add(t);
		}
		return walletTrans;
	}

}
