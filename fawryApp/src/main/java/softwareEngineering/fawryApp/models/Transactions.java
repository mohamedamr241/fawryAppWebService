package softwareEngineering.fawryApp.models;

import java.util.*;

public class Transactions {
	public static ArrayList<TransactionEntity> transactions = new ArrayList<TransactionEntity>();
	public static Map<String, Integer> userTransactionNumber = new HashMap<String, Integer>();
	public static void addTransaction(int id, double amount,String serviceProvider ,String serviceName, String email)
	{
		TransactionEntity obj = new TransactionEntity(id, amount,serviceProvider ,serviceName, email);
		transactions.add(obj);
	}

}
