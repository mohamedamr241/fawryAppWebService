package softwareEngineering.fawryApp.bsl;

import softwareEngineering.fawryApp.models.Etisalat;
import softwareEngineering.fawryApp.models.Orange;
import softwareEngineering.fawryApp.models.ServiceProviders;
import softwareEngineering.fawryApp.models.Vodafone;
import softwareEngineering.fawryApp.models.We;

public class MobileService extends Services{
	
	public MobileService()
	{
		super();
	}
	protected void setProviders()
	{
		serviceProviders.add("We");
		serviceProviders.add("Etisalat");
		serviceProviders.add("Orange");
		serviceProviders.add("Vodafone");
	}
	
	protected void setPayMethods()
	{
		paymentMethods.add("CreditCard");
		paymentMethods.add("Cash");
		paymentMethods.add("Wallet");
	}
	
	public ServiceProviders createServiceProvider(String n) {
		ServiceProviders sp = null;
		if(n.equals("We")) {
			providerHandler=new WeHandler();
			sp = new We();
		}
		else if(n.equals("Etisalat")) {
			providerHandler=new EtisalatHnadler();
			sp = new Etisalat();
		}
		else if(n.equals("Orange")) {
			providerHandler=new OrangeHandler();
			sp = new Orange();
		}
		else if(n.equals("Vodafone")) {
			providerHandler=new VodafoneHandler();
			sp = new Vodafone();
		}
		return sp;

		
	}



}
