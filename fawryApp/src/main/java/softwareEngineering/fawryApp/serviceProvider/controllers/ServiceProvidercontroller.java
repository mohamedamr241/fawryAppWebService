package softwareEngineering.fawryApp.serviceProvider.controllers;

import java.util.ArrayList;
import java.util.Arrays;

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
public class ServiceProvidercontroller {

		
		@GetMapping(value="/services/{serviceName}/serviceProviders")
		public ResponseEntity<ArrayList<String>> serviceProviders(@PathVariable("serviceName") String serviceName) {
			
			String serveName = Services.getServiceNameById(serviceName);
			if(Services.displayServices().contains(serveName))
			{
				Services s = null;
				if(serveName.equals("MobileRecharge")) 
					s = new MobileService();
				
				else if(serveName.equals("Landline")) 
					s = new LandlineService();
				
				else if(serveName.equals("InternetPayment")) 
					s = new InternetService();
				
				else if(serveName.equals("Donations"))
					s = new DonationService();	
				return ResponseEntity.ok(s.displayProviders());
			}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<String>(Arrays.asList("Error", "Provider Not Found")));
	     }
		
}