package softwareEngineering.fawryApp.discount.bsl;

import softwareEngineering.fawryApp.payment.bsl.Payment;

public abstract class Discount implements Payment{
	public abstract double pay(double price);
}