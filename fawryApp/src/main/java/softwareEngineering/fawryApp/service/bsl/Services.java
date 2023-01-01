package softwareEngineering.fawryApp.service.bsl;


import java.util.*;

import softwareEngineering.fawryApp.serviceProvider.handlers.Handler;
import softwareEngineering.fawryApp.serviceProvider.models.ServiceProviders;
import softwareEngineering.fawryApp.payment.bsl.Payment;


public abstract class Services {
	public Handler providerHandler;
	Payment payMethod;
	private static ArrayList<String> services = new ArrayList<String>(Arrays.asList("MobileRecharge","Landline","InternetPayment","Donations"));
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
	
	public static String getServiceNameById(String id)
	{
		if(id.equals("1") || id.equals("MobileRecharge"))
			return "MobileRecharge";
		else if(id.equals("2") || id.equals("Landline"))
			return "Landline";
		else if(id.equals("3") || id.equals("InternetPayment"))
			return "InternetPayment";
		else if(id.equals("4") || id.equals("Donations"))
			return "Donations";
		return null;

	}
	
}
