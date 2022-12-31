package softwareEngineering.fawryApp.bsl;

import java.util.Map;

import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.models.ServiceProviders;


@Service
public class TransactionBsl{
	
	ServiceProviders servprovider;
	Services service;
	
	public Map<String, String> createTransaction(String serviceName, String serviceProvider, String paymentMethod) {//, PaymentBsl payment)
		
		
		if(Services.displayServices().contains(serviceName) || serviceName.equals("1") || serviceName.equals("2") || serviceName.equals("3") || serviceName.equals("4"))
		{
			if(serviceName.equals("mobileRecharge") || serviceName.equals("1")) {
				service = new MobileService();
				if(service.displayProviders().contains(serviceProvider))
				{
					servprovider = service.orderServiceProvider(serviceProvider);
//					return payment.pay(service, "mobileRecharge", serviceProvider, paymentMethod);	
				}
			}
			else if(serviceName.equals("Landline")|| serviceName.equals("2")){
				service = new LandlineService();
				if(service.displayProviders().contains(serviceProvider))
				{
					servprovider = service.orderServiceProvider(serviceName);
//					return payment.pay(service, "Landline", serviceProvider, paymentMethod);				
				}

			}
			else if(serviceName.equals("InternetPayment")|| serviceName.equals("3")){
				service = new InternetService();
				if(service.displayProviders().contains(serviceProvider))
				{
					servprovider = service.orderServiceProvider(serviceName);
//					return payment.pay(service, "InternetPayment", serviceProvider, paymentMethod);				
				}

			}
			else if(serviceName.equals("Donations")|| serviceName.equals("4"))
			{
				service = new DonationService();
				if(service.displayProviders().contains(serviceProvider))
				{
					servprovider = service.orderServiceProvider(serviceName);
//					return payment.pay(service, "Donations", serviceProvider, paymentMethod);				
				}

			}
				}
		//response
		return null;

	}
}