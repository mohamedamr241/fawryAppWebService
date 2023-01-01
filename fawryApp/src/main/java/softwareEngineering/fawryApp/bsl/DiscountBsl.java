package softwareEngineering.fawryApp.bsl;

import java.util.*;
import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.models.Discounts;

@Service
public class DiscountBsl{
	
	public SpecificDiscount specificDis = new SpecificDiscount(null);
	
	public Map<String, String> getDiscounts() {
		Map<String, String> disc = new HashMap<String,String>();
		for(Map.Entry<String, Integer> entry : Discounts.getDiscounts().entrySet())
		{		
			disc.put(entry.getKey() + " service", " has discount "+entry.getValue()+" %");
		}
		return disc;
	}
	
	public String addDiscount(String serviceName, int discount) {
		
		if(Services.displayServices().contains(serviceName))
		{
			Discounts.addDiscount(serviceName, discount);
			UserBsl.notify(serviceName + " has discount " + discount + " %");
			return "Discount Added Successfully";
		}
		return "This service is not availabe";
	}
}