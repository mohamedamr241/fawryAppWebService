package softwareEngineering.fawryApp.models;

public class TransactionEntity {
	public int transId;
	public double amount;
	public String serviceName;
	public String email;
<<<<<<< Updated upstream
	public String serviceProvider,payMethod ;
	public TransactionEntity(int transId, double amount,String serviceProvider, String serviceName, String email , String payMethod) {
		this.transId = transId;
		this.amount = amount;
		this.serviceName = serviceName;
		this.email = email;
		this.serviceProvider = serviceProvider;
		this.payMethod = payMethod ;
=======
	public String serviceProvider, payMethod;
	public TransactionEntity(int transId, double amount,String serviceProvider, String serviceName, String userEmail, String payMethod) {
		this.transId = transId;
		this.amount = amount;
		this.serviceName = serviceName;
		this.email = userEmail;
		this.serviceProvider = serviceProvider;
		this.payMethod = payMethod;
>>>>>>> Stashed changes
	}
	
	public String toString() {
		return "Email: " + email +
	    		", Transaction No: " + transId + 
	    		", Service name: " + serviceName +
	    		", Service provider name: " + serviceProvider +
	           ", Amount: " + amount;
	    
	}
}
