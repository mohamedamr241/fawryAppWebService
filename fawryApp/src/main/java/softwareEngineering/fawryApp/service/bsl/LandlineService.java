package softwareEngineering.fawryApp.service.bsl;


import softwareEngineering.fawryApp.serviceProvider.models.MonthlyReceipt;
import softwareEngineering.fawryApp.serviceProvider.models.QuarterReceipt;
import softwareEngineering.fawryApp.serviceProvider.models.ServiceProviders;


public class LandlineService extends Services{

	public LandlineService()
	{
		super();
	}
	
	@Override
	public ServiceProviders createServiceProvider(String n) {
		ServiceProviders sp = null;
		
		if(n.equals("MonthlyReceipt"))
			sp = new MonthlyReceipt();
		else if (n.equals("QuarterReceipt"))
			sp = new QuarterReceipt();
		
		return sp;
	}
	@Override
	protected void setPayMethods() {
		paymentMethods.add("CreditCard");
		paymentMethods.add("Cash");		
	}
	@Override
	protected void setProviders() {
		serviceProviders.add("MonthlyReceipt");
		serviceProviders.add("QuarterReceipt");		
	}

}
