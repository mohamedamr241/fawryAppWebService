package softwareEngineering.fawryApp.service.bsl;

import softwareEngineering.fawryApp.serviceProvider.handlers.HospitalHandler;
import softwareEngineering.fawryApp.serviceProvider.models.CancerHospital;
import softwareEngineering.fawryApp.serviceProvider.models.NGOs;
import softwareEngineering.fawryApp.serviceProvider.models.Schools;
import softwareEngineering.fawryApp.serviceProvider.models.ServiceProviders;

public class DonationService extends Services {


	public DonationService()
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
		serviceProviders.add("CancerHospital");
		serviceProviders.add("Schools");
		serviceProviders.add("NGOs");		
	}	
	
	public ServiceProviders createServiceProvider(String n) {
		ServiceProviders sp = null;
		if(n.equals("CancerHospital")) {
			providerHandler=new HospitalHandler();
			sp = new CancerHospital();
		}
		else if(n.equals("Schools"))
			sp = new Schools();
		else if(n.equals("NGOs"))
			sp = new NGOs();
		
		return sp;

		
	}

}
