package softwareEngineering.fawryApp.controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.bsl.DonationService;
import softwareEngineering.fawryApp.bsl.InternetService;
import softwareEngineering.fawryApp.bsl.LandlineService;
import softwareEngineering.fawryApp.bsl.MobileService;
import softwareEngineering.fawryApp.bsl.Services;


@RestController
public class ServiceProvidercontroller {//response
	
	@GetMapping(value="/services/{serviceName}/serviceProviders")
	public ArrayList<String> serviceProviders(@PathVariable("serviceName") String serviceName)
	{
		Services s = null;
		if(serviceName.equals("mobileRecharge") || serviceName.equals("1") ) {
			
			s = new MobileService();
			return s.displayProviders();
		}
		else if(serviceName.equals("Landline") || serviceName.equals("2")) {
			s = new LandlineService();
			return s.displayProviders();
		}
		else if(serviceName.equals("InternetPayment") || serviceName.equals("3")) {
			s = new InternetService();
			return s.displayProviders();
		}
		else if(serviceName.equals("Donations") || serviceName.equals("4")) {
			s = new DonationService();
			return s.displayProviders();
		}
		return null;
	}
}