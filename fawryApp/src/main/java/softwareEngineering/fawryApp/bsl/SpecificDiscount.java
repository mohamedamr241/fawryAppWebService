package softwareEngineering.fawryApp.bsl;
import java.util.*;

public class SpecificDiscount extends Discount {
	public static Map<String, Integer> serviceDiscount = new HashMap<String, Integer>();
	Payment payment;
	static String serviceTmp;
	public SpecificDiscount(Payment payment)
	{
		this.payment = payment;
	}
	
	public static boolean searchService(String service)
	{
		for(String s : serviceDiscount.keySet())
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
		double discount = serviceDiscount.get(serviceTmp);
		return payment.pay(price) - (price *(discount/100));
	}
	
}