package softwareEngineering.fawryApp.models;

public class TransactionEntity {
	public int transId;
	public double amount;
	public String serviceName;
	public String userEmail;
	public TransactionEntity(int transId, double amount,String serviceName, String userEmail) {
		this.transId = transId;
		this.amount = amount;
		this.serviceName = serviceName;
		this.userEmail = userEmail;
	}
	
	public String toString() {
	    return "transaction No: " + transId + 
	           ", amount: " + amount +
	           ", service name: " + serviceName +
	           ", your email: " + userEmail;

	    
	}
}
