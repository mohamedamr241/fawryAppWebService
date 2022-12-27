package softwareEngineering.fawryApp.controllers;


import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.bsl.Admin;
import softwareEngineering.fawryApp.bsl.Services;
import softwareEngineering.fawryApp.bsl.SpecificDiscount;

@RestController
public class discountController{
	@RequestMapping(value="/user/discounts",method = RequestMethod.GET)
	public Map<String, String> discounts() {
		Map<String, String>disc=new HashMap<String,String>();
		for(Map.Entry<String, Integer> entry : SpecificDiscount.serviceDiscount.entrySet())
		{		
			disc.put("Service "+entry.getKey(), " has discount "+entry.getValue()+" $");
		}
		
		return disc;
	}
	
	@RequestMapping(value="/admin/discounts/{serviceName}/{discount}",method = RequestMethod.POST)
	public String addDiscount(@PathVariable("serviceName") String serviceName,@PathVariable("discount") int discount) {
		boolean check =false;
		for(int i=0;i<Services.services.size();i++) {
			if(serviceName.equals(Services.services.get(i))) {
				check = true;
				break;
			}
		}
		if(check) {
			Admin admin = new Admin();
			admin.addDiscount(serviceName, discount);
			return "Discount Added Successfully";			
		}
		return "This service is not availabe";
	}
}