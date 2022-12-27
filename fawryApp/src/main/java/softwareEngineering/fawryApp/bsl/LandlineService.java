package softwareEngineering.fawryApp.bsl;

import softwareEngineering.fawryApp.models.MonthlyReceipt;
import softwareEngineering.fawryApp.models.QuarterReceipt;
import softwareEngineering.fawryApp.models.ServiceProviders;

public class LandlineService extends Services{

	
	@Override
	protected void setData() {
		paymentMethods.add("CreditCard");
		paymentMethods.add("Cash");
		serviceProviders.add("MonthlyReceipt");
		serviceProviders.add("QuarterReceipt");
		
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

//	@Override
//	public void displayPaymentForm() {
//		// TODO Auto-generated method stub
//		
//	}




	
	


}
