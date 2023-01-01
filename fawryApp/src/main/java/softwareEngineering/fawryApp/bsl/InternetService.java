package softwareEngineering.fawryApp.bsl;


import softwareEngineering.fawryApp.models.Etisalat;
import softwareEngineering.fawryApp.models.Orange;
import softwareEngineering.fawryApp.models.ServiceProviders;
import softwareEngineering.fawryApp.models.Vodafone;
import softwareEngineering.fawryApp.models.We;

public class InternetService extends Services{

	
	public InternetService()
	{
		super();
	}
	
	@Override
	protected void setPayMethods() {
		paymentMethods.add("CreditCard");
		paymentMethods.add("Cash");		
	}
	@Override
	protected void setProviders() {
		serviceProviders.add("We");
		serviceProviders.add("Etisalat");
		serviceProviders.add("Orange");
		serviceProviders.add("Vodafone");		
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
			//providerHandler=new OrangeHnadler();
			sp = new Orange();
		}
		else if(n.equals("Vodafone")) {
			//providerHandler=new VodafoneHnadler();
			sp = new Vodafone();
		}
		return sp;

		
	}
}
