package softwareEngineering.fawryApp.discount.bsl;

import java.util.*;
import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.discount.models.Discounts;
import softwareEngineering.fawryApp.service.bsl.Services;
import softwareEngineering.fawryApp.user.bsl.UserBsl;

@Service
public class DiscountBsl {
	
	public SpecificDiscount specificDis = new SpecificDiscount(null);
	
	public Map<String, String> getDiscounts() {
		Map<String, String> disc = new HashMap<String,String>();
		for(Map.Entry<String, Integer> entry : Discounts.getDiscounts().entrySet())
		{		
			disc.put(entry.getKey() + " service", " has discount "+entry.getValue()+" %");
		}
		return disc;
	}
	
	public boolean addDiscount(String serviceName, int discount) {
		
		if(Services.displayServices().contains(serviceName))
		{
			Discounts.addDiscount(serviceName, discount);
			UserBsl.notify(serviceName + " has discount " + discount + " %");
			return true;
		}
		return false;
	}
}