package softwareEngineering.fawryApp.discount.bsl;
import java.util.*;

import softwareEngineering.fawryApp.discount.models.Discounts;
import softwareEngineering.fawryApp.payment.bsl.Payment;

public class SpecificDiscount extends Discount {
	Payment payment;
	private static String serviceTmp;
		
	public SpecificDiscount(Payment payment)
	{
		this.payment = payment;
	}
	public static int checkDiscount(String service)
	{
		for(Map.Entry<String, Integer> set : Discounts.getDiscounts().entrySet())
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
		double discount = Discounts.getDiscounts().get(serviceTmp);
		return payment.pay(price) - (price *(discount/100));
	}
	
}