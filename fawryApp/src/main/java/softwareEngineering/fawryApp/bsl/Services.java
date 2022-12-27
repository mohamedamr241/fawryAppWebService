package softwareEngineering.fawryApp.bsl;

import java.util.ArrayList;
import java.util.Arrays;

import softwareEngineering.fawryApp.models.ServiceProviders;

public abstract class Services {
	public Handler providerHandler;
	Payment payMethod;
	public static ArrayList<String> services = new ArrayList<String>(Arrays.asList("MobileRecharge","Landline","InternetPayment","Donations"));
	ArrayList<String> serviceProviders = new ArrayList<String>();
	ArrayList<String> paymentMethods = new ArrayList<String>();
	public Services()
	{
		setData();
	}
	public abstract ServiceProviders createServiceProvider(String num);
	protected abstract void setData();
	public ArrayList<String> displayProviders() {
		return serviceProviders;
	}
	public ServiceProviders orderServiceProvider(String num) {
		ServiceProviders serv = createServiceProvider(num);
		return serv;
	}
	
	public ArrayList<String> displayPayMethods() {
		return paymentMethods;
	}
	
	public void setPayMethod(Payment payMethod)
	{
		this.payMethod = payMethod;
	}
	
	public double performPayMethod(double price)
	{
		double disPrice = payMethod.pay(price);
		System.out.println("price after discount: "+disPrice);
		return disPrice;
	}
	
}
