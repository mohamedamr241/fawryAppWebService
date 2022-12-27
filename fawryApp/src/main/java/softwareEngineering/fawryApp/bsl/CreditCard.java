package softwareEngineering.fawryApp.bsl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CreditCard implements Payment{
	static ArrayList<String> creditCardInfo = new ArrayList<String>(Arrays.asList("Credit card number","CCN"));
	public double pay(double amount) {
		return amount;
	}
	public static void creditCardForm() {
	for(String s : creditCardInfo)
		System.out.println(s);
	
	}
}


