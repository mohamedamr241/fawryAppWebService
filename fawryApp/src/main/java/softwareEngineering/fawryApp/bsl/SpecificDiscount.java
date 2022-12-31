package softwareEngineering.fawryApp.bsl;
import java.util.*;

public class SpecificDiscount extends Discount {
	private static Map<String, Integer> serviceDiscounts = new HashMap<String, Integer>();//service-discountValue
	Payment payment;
	private static String serviceTmp;
		
	public SpecificDiscount(Payment payment)
	{
		this.payment = payment;
	}
	
	public void addDiscount(String serviceName, int dis)
	{
		serviceDiscounts.put(serviceName, dis);
	}
	
	public Map<String, Integer> getDiscounts()
	{
		return serviceDiscounts;
	}
	
	
	
	public static int checkDiscount(String service)
	{
		for(Map.Entry<String, Integer> set : serviceDiscounts.entrySet())
		{
			if(set.getKey().equals(service))
			{
				serviceTmp = service;
				return set.getValue();
			}
		}
		return 0;
	}
	
	
	public double pay(double price) {
		double discount = serviceDiscounts.get(serviceTmp);
		return payment.pay(price) - (price *(discount/100));
	}
	
}