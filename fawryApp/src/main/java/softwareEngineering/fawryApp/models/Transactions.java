package softwareEngineering.fawryApp.models;

import java.util.*;

public class Transactions {
	public static ArrayList<TransactionEntity> transactions = new ArrayList<TransactionEntity>();
	public static Map<String, Integer> userTransactionNumber = new HashMap<String, Integer>();
	public static void addTransaction(int id, double amount, String serviceName, String email)
	{
		TransactionEntity obj = new TransactionEntity(id, amount, serviceName, email);
		transactions.add(obj);
	}

}
