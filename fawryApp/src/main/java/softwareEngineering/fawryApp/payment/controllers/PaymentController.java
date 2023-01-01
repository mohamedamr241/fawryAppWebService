package softwareEngineering.fawryApp.payment.controllers;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.service.bsl.DonationService;
import softwareEngineering.fawryApp.service.bsl.InternetService;
import softwareEngineering.fawryApp.service.bsl.LandlineService;
import softwareEngineering.fawryApp.service.bsl.MobileService;
import softwareEngineering.fawryApp.service.bsl.Services;


@RestController
public class PaymentController{
	

	@GetMapping(value="/services/{serviceName}/paymentMethods")
	public ResponseEntity<ArrayList<String>> paymentMethods(@PathVariable("serviceName") String serviceName) {
		
		if(Services.displayServices().contains(serviceName) || serviceName.equals("1") || serviceName.equals("2") || serviceName.equals("3") || serviceName.equals("4"))
		{
			Services s = null;
			String serveName = Services.getServiceNameById(serviceName);
			if(serveName.equals("MobileRecharge")) 
				s = new MobileService();
			
			else if(serveName.equals("Landline")) 
				s = new LandlineService();
			
			else if(serveName.equals("InternetPayment")) 
				s = new InternetService();
			
			else if(serveName.equals("Donations"))
				s = new DonationService();	
			return ResponseEntity.ok(s.displayPayMethods());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<String>(Arrays.asList("Error", "Payment Method Not Found")));
     }
	
}
