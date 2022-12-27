package softwareEngineering.fawryApp.bsl;

import softwareEngineering.fawryApp.models.CancerHospital;
import softwareEngineering.fawryApp.models.NGOs;
import softwareEngineering.fawryApp.models.Schools;
import softwareEngineering.fawryApp.models.ServiceProviders;

public class DonationService extends Services {

	protected void setData()
	{
		paymentMethods.add("CreditCard");
		paymentMethods.add("Cash");
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
