package softwareEngineering.fawryApp.bsl;

<<<<<<< Updated upstream
import java.util.Map;

=======
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
>>>>>>> Stashed changes
import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.models.ServiceProviders;


@Service
public class TransactionBsl{
	
	ServiceProviders servprovider;
	Services service;
	
<<<<<<< Updated upstream
	public Map<String, String> createTransaction(String serviceName, String serviceProvider, String paymentMethod) {//, PaymentBsl payment)
		
		
=======
	public ResponseEntity<Map<String, String>> createTransaction(String serviceName, String serviceProvider, String paymentMethod, PaymentBsl payment) {
		
		Map<String, String> transactionDetails = new HashMap<String, String>();
>>>>>>> Stashed changes
		if(Services.displayServices().contains(serviceName) || serviceName.equals("1") || serviceName.equals("2") || serviceName.equals("3") || serviceName.equals("4"))
		{
			if(serviceName.equals("mobileRecharge") || serviceName.equals("1")) {
				service = new MobileService();
				if(service.displayProviders().contains(serviceProvider))
				{
					servprovider = service.orderServiceProvider(serviceProvider);
<<<<<<< Updated upstream
//					return payment.pay(service, "mobileRecharge", serviceProvider, paymentMethod);	
=======
					transactionDetails = payment.pay(service, "mobileRecharge", serviceProvider, paymentMethod);
>>>>>>> Stashed changes
				}
			}
			else if(serviceName.equals("Landline")|| serviceName.equals("2")){
				service = new LandlineService();
				if(service.displayProviders().contains(serviceProvider))
				{
					servprovider = service.orderServiceProvider(serviceName);
<<<<<<< Updated upstream
//					return payment.pay(service, "Landline", serviceProvider, paymentMethod);				
=======
					transactionDetails = payment.pay(service, "Landline", serviceProvider, paymentMethod);
>>>>>>> Stashed changes
				}

			}
			else if(serviceName.equals("InternetPayment")|| serviceName.equals("3")){
				service = new InternetService();
				if(service.displayProviders().contains(serviceProvider))
				{
					servprovider = service.orderServiceProvider(serviceName);
<<<<<<< Updated upstream
//					return payment.pay(service, "InternetPayment", serviceProvider, paymentMethod);				
=======
					transactionDetails = payment.pay(service, "InternetPayment", serviceProvider, paymentMethod);
>>>>>>> Stashed changes
				}

			}
			else if(serviceName.equals("Donations")|| serviceName.equals("4"))
			{
				service = new DonationService();
				if(service.displayProviders().contains(serviceProvider))
				{
					servprovider = service.orderServiceProvider(serviceName);
<<<<<<< Updated upstream
//					return payment.pay(service, "Donations", serviceProvider, paymentMethod);				
				}

			}
				}
		//response
		return null;
=======
					transactionDetails = payment.pay(service, "Donations", serviceProvider, paymentMethod);
				}
			}
			return ResponseEntity.ok(transactionDetails);
				}
		transactionDetails.put("error", "Service not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(transactionDetails);
>>>>>>> Stashed changes

	}
}