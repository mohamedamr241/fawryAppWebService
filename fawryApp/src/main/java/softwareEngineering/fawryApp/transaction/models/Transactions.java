package softwareEngineering.fawryApp.transaction.models;

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
	
	public static TransactionEntity getTransactionById(int id)
	{
		for(TransactionEntity t : transactions)
		{
			if(t.getTransId() == id)
				return t;
		}
		return null;
	}
	
	public static void removeTransactionById(int id)
	{
		for(TransactionEntity t : transactions)
		{
			if(t.getTransId() == id)
				transactions.remove(t);
		}
	}

}
