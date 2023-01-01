package softwareEngineering.fawryApp.bsl;

import java.util.*;
import org.springframework.stereotype.Service;

@Service
public class DiscountBsl{
	
	public SpecificDiscount specificDis = new SpecificDiscount(null);
	
	public Map<String, String> getDiscounts() {
		Map<String, String> disc = new HashMap<String,String>();
		for(Map.Entry<String, Integer> entry : specificDis.getDiscounts().entrySet())
		{		
			disc.put(entry.getKey() + " service", " has discount "+entry.getValue()+" %");
		}
		return disc;
	}
	
	public boolean addDiscount(String serviceName, int discount) {
		
		String serveName = Services.getServiceNameById(serviceName);
		if(Services.displayServices().contains(serveName))
		{
			specificDis.addDiscount(serveName, discount);
			UserBsl.notify(serveName + " has discount " + discount + " %");
			return true;
		}
		return false;
	}
}