package softwareEngineering.fawryApp.controllers;


import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


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
}