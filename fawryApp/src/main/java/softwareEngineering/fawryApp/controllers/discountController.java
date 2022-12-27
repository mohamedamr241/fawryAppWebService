package softwareEngineering.fawryApp.controllers;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.bsl.Services;
import softwareEngineering.fawryApp.bsl.SpecificDiscount;

@RestController
public class discountController{
	@RequestMapping(value="/user/discounts",method = RequestMethod.GET)
	public ArrayList<String> discounts() {
		for(Map.Entry<String, Integer> entry : SpecificDiscount.serviceDiscount.entrySet())
		{
			System.out.print("Service " + entry.getKey());
			System.out.println(" has discount " + entry.getValue() + " $");
		}
		
		return Services.services;
	}
}