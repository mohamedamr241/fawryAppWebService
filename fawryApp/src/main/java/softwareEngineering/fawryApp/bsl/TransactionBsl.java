package softwareEngineering.fawryApp.bsl;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.models.ServiceProviders;


@Service
public class TransactionBsl{
	
	ServiceProviders servprovider;
	Services service;
	
	public ResponseEntity<Map<String, String>> createTransaction(String serviceName, String serviceProvider, String paymentMethod, PaymentBsl payment) {
		
		Map<String, String> transactionDetails = new HashMap<String, String>();
		if(Services.displayServices().contains(serviceName) || serviceName.equals("1") || serviceName.equals("2") || serviceName.equals("3") || serviceName.equals("4"))
		{
			if(serviceName.equals("mobileRecharge") || serviceName.equals("1")) {
				service = new MobileService();
				if(service.displayProviders().contains(serviceProvider))
				{
					servprovider = service.orderServiceProvider(serviceProvider);
					transactionDetails = payment.pay(service, "mobileRecharge", serviceProvider, paymentMethod);
				}
			}
			else if(serviceName.equals("Landline")|| serviceName.equals("2")){
				service = new LandlineService();
				if(service.displayProviders().contains(serviceProvider))
				{
					servprovider = service.orderServiceProvider(serviceName);
					transactionDetails = payment.pay(service, "Landline", serviceProvider, paymentMethod);
				}

			}
			else if(serviceName.equals("InternetPayment")|| serviceName.equals("3")){
				service = new InternetService();
				if(service.displayProviders().contains(serviceProvider))
				{
					servprovider = service.orderServiceProvider(serviceName);
					transactionDetails = payment.pay(service, "InternetPayment", serviceProvider, paymentMethod);
				}

			}
			else if(serviceName.equals("Donations")|| serviceName.equals("4"))
			{
				service = new DonationService();
				if(service.displayProviders().contains(serviceProvider))
				{
					servprovider = service.orderServiceProvider(serviceName);
					transactionDetails = payment.pay(service, "Donations", serviceProvider, paymentMethod);
				}
			}
			return ResponseEntity.ok(transactionDetails);
				}
		transactionDetails.put("error", "Service not found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(transactionDetails);

	}
}