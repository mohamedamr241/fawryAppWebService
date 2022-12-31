package softwareEngineering.fawryApp.bsl;

import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.models.MonthlyReceipt;
import softwareEngineering.fawryApp.models.QuarterReceipt;
import softwareEngineering.fawryApp.models.ServiceProviders;


//@Service
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

//	@Override
//	public void displayPaymentForm() {
//		// TODO Auto-generated method stub
//		
//	}




	
	


}
