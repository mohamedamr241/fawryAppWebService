package softwareEngineering.fawryApp.models;

public class TransactionEntity {
	private int transId;
	private double amount;
	private String serviceName, email;
	private String serviceProvider, payMethod, timeStamp;
	public TransactionEntity(int transId, double amount,String serviceProvider, String serviceName, String userEmail, String payMethod) {
		this.transId = transId;
		this.amount = amount;
		this.serviceName = serviceName;
		this.email = userEmail;
		this.serviceProvider = serviceProvider;
		this.payMethod = payMethod;
	}
	
    public String getServiceProvider() {
        return serviceProvider;
    }
    public String getPayMethod() {
        return payMethod;
    }
    public int getTransId() {
        return transId;
    }
    public double getAmount() {
        return amount;
    }
    public String getServiceName() {
        return serviceName;
    }
    public String getEmail() {
        return email;
    }
    public String getTimeStamp() {
        return timeStamp;
    }
}
