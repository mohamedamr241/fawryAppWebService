package softwareEngineering.fawryApp.models;

public class TransactionEntity {
	public int transId;
	public double amount;
	public String serviceName;
	public String userEmail;
	public String serviceProvider;//??
	public TransactionEntity(int transId, double amount,String serviceProvider, String serviceName, String userEmail) {
		this.transId = transId;
		this.amount = amount;
		this.serviceName = serviceName;
		this.userEmail = userEmail;
		this.serviceProvider = serviceProvider;
	}
	
	public String toString() {
		return "Email: " + userEmail +
	    		", Transaction No: " + transId + 
	    		", Service name: " + serviceName +
	    		", Service provider name: " + serviceProvider +
	           ", Amount: " + amount;
	    
	}
}
