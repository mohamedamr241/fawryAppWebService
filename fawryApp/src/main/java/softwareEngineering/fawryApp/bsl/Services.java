package softwareEngineering.fawryApp.bsl;

import java.util.*;

import softwareEngineering.fawryApp.models.ServiceProviders;


public abstract class Services {
	public Handler providerHandler;
	Payment payMethod;
	private static ArrayList<String> services = new ArrayList<String>(Arrays.asList("mobileRecharge","Landline","InternetPayment","Donations"));
	protected ArrayList<String> serviceProviders = new ArrayList<String>();
	protected ArrayList<String> paymentMethods = new ArrayList<String>();

	public Services()
	{
		setProviders();
		setPayMethods();
	}
	
	public abstract ServiceProviders createServiceProvider(String num);
	protected abstract void setPayMethods();
	protected abstract void setProviders();
	
	public ArrayList<String> displayProviders() {
		return serviceProviders;
	}
	
	public ArrayList<String> displayPayMethods() {
		return paymentMethods;
	}
	
	public static ArrayList<String> displayServices() {
		return services;
	}
	
	public ServiceProviders orderServiceProvider(String num) {
		ServiceProviders serv = createServiceProvider(num);
		return serv;
	}
	
	public void setPayMethod(Payment payMethod)
	{
		this.payMethod = payMethod;
	}
	
	public double performPayMethod(double price)
	{
		double disPrice = payMethod.pay(price);
		return disPrice;
	}
	
}
