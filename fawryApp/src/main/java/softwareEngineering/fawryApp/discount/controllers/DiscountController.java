package softwareEngineering.fawryApp.discount.controllers;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.admin.models.Admin;
import softwareEngineering.fawryApp.discount.bsl.DiscountBsl;
import softwareEngineering.fawryApp.service.bsl.Services;
import softwareEngineering.fawryApp.timeStampBsl.TimeStampBsl;



@RestController
public class DiscountController{
	
	private DiscountBsl discountbsl;
	
	public DiscountController(DiscountBsl discountbsl)
	{
		this.discountbsl = discountbsl;
	}
	
	@GetMapping(value="/discounts")
	public ResponseEntity<Map<String, String>> discounts() {
		if(discountbsl.getDiscounts().size() == 0)
			return ResponseEntity.ok(new HashMap<String, String>(Map.of("discounts", "no discounts available"))) ;
		return ResponseEntity.ok(discountbsl.getDiscounts());
	}
	
	@GetMapping(value="/admin/discounts/{serviceName}/{discount}")
	public ResponseEntity<String> addDiscount(@PathVariable("serviceName") String serviceName,@PathVariable("discount") int discount,@RequestBody Admin ad) {
		String serveName = Services.getServiceNameById(serviceName);
		if(TimeStampBsl.checkValidationAdmin(ad.timeStamp)) {
			if(discountbsl.addDiscount(serveName, discount))
				return ResponseEntity.ok("Discount Added Successfully");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error, Service Not Found");
		}
		return  ResponseEntity.ok("you must signIn first");
	}
}