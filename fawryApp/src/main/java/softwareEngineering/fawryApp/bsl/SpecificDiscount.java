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
	
	
	
	public static boolean checkDiscount(String service)
	{
		for(String s : serviceDiscounts.keySet())
		{
			if(s.equals(service))
			{
				serviceTmp = service;
				return true;
			}
		}
		return false;
	}
	
	
	public double pay(double price) {
		double discount = serviceDiscounts.get(serviceTmp);
		return payment.pay(price) - (price *(discount/100));
	}
	
}