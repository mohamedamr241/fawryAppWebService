package softwareEngineering.fawryApp.discount.models;

import java.util.HashMap;
import java.util.Map;

public class Discounts {
	private static Map<String, Integer> serviceDiscounts = new HashMap<String, Integer>();//service-discountValue

	public static void addDiscount(String serviceName, int dis)
	{
		serviceDiscounts.put(serviceName, dis);
	}
	
	public static Map<String, Integer> getDiscounts()
	{
		return serviceDiscounts;
	}
}