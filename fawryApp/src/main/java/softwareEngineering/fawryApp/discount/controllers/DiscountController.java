package softwareEngineering.fawryApp.discount.controllers;

import java.util.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.admin.models.Admin;
import softwareEngineering.fawryApp.discount.bsl.DiscountBsl;
import softwareEngineering.fawryApp.timeStampBsl.TimeStampBsl;



@RestController
public class DiscountController{
	
	private DiscountBsl discountbsl;
	
	public DiscountController(DiscountBsl discountbsl)
	{
		this.discountbsl = discountbsl;
	}
	
	@GetMapping(value="/discounts")
	public Map<String, String> discounts() {
		return discountbsl.getDiscounts();
	}
	
	@GetMapping(value="/admin/discounts/{serviceName}/{discount}")
	public String addDiscount(@PathVariable("serviceName") String serviceName,@PathVariable("discount") int discount,@RequestBody Admin ad) {
		if(TimeStampBsl.checkValidationAdmin(ad.timeStamp)) {
			return discountbsl.addDiscount(serviceName, discount);			
		}
		return "you must signIn first";
	}
}