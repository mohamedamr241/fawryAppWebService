package softwareEngineering.fawryApp.discount.bsl;

import softwareEngineering.fawryApp.payment.bsl.Payment;

public class OverallDiscount extends Discount{
	Payment payment;
	public OverallDiscount (Payment serv) {
		payment=serv;
	}
	@Override
	public double pay(double price) {
		return payment.pay(price-(price*0.1));
	}
}
