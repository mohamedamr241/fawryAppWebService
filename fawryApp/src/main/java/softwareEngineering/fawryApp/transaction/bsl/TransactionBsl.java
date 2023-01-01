package softwareEngineering.fawryApp.transaction.bsl;

import java.util.*;

import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.payment.bsl.PaymentBsl;
import softwareEngineering.fawryApp.service.bsl.DonationService;
import softwareEngineering.fawryApp.service.bsl.InternetService;
import softwareEngineering.fawryApp.service.bsl.LandlineService;
import softwareEngineering.fawryApp.service.bsl.MobileService;
import softwareEngineering.fawryApp.service.bsl.Services;
import softwareEngineering.fawryApp.serviceProvider.models.ServiceProviders;
import softwareEngineering.fawryApp.timeStampBsl.TimeStampBsl;


@Service
public class TransactionBsl{
	
	ServiceProviders servprovider;
	Services service;
	
	public Map<String, String> createTransaction(String serviceName, String serviceProvider, String paymentMethod, PaymentBsl payment) {
		
		if(TimeStampBsl.checkValidation(payment.timeStamp,payment.email))
		{
			String serveName = Services.getServiceNameById(serviceName);
			if(Services.displayServices().contains(serveName))
			{
				if(serveName.equals("MobileRecharge")) 
					service = new MobileService();
				
				else if(serveName.equals("Landline"))
					service = new LandlineService();
				
				else if(serveName.equals("InternetPayment"))
					service = new InternetService();
				
				else if(serveName.equals("Donations"))
					service = new DonationService();
				
				if(service.displayProviders().contains(serviceProvider))
				{
					servprovider = service.orderServiceProvider(serviceProvider);
					if(!service.displayPayMethods().contains(paymentMethod))
						return new HashMap<String, String>(Map.of("Error","Payment Method Not Found"));
				}
				else 
					return new HashMap<String, String>(Map.of("Error","Provider Not Found"));
				
				return payment.makePurchase(service, serveName, serviceProvider, paymentMethod);
			}
			return new HashMap<String, String>(Map.of("Error","Service Not Found"));
		}
		else 
		return new HashMap<String, String>(Map.of("Error","You must signIn first"));
	}
}
