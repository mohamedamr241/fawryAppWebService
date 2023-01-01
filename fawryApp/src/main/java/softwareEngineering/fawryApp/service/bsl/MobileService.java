package softwareEngineering.fawryApp.service.bsl;


import softwareEngineering.fawryApp.serviceProvider.handlers.EtisalatHnadler;
import softwareEngineering.fawryApp.serviceProvider.handlers.OrangeHandler;
import softwareEngineering.fawryApp.serviceProvider.handlers.VodafoneHandler;
import softwareEngineering.fawryApp.serviceProvider.handlers.WeHandler;
import softwareEngineering.fawryApp.serviceProvider.models.Etisalat;
import softwareEngineering.fawryApp.serviceProvider.models.Orange;
import softwareEngineering.fawryApp.serviceProvider.models.ServiceProviders;
import softwareEngineering.fawryApp.serviceProvider.models.Vodafone;
import softwareEngineering.fawryApp.serviceProvider.models.We;

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
