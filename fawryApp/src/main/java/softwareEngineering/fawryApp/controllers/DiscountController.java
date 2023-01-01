package softwareEngineering.fawryApp.controllers;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import softwareEngineering.fawryApp.bsl.DiscountBsl;


@RestController
public class DiscountController{
	
	DiscountBsl discountbsl;
	
	public DiscountController(DiscountBsl discountbsl)
	{
		this.discountbsl = discountbsl;
	}
	
	@GetMapping(value="/discounts")
	public ResponseEntity<Map<String, String>> discounts() {
		if(discountbsl.getDiscounts().size() == 0)
			return ResponseEntity.noContent().build();
		return ResponseEntity.ok(discountbsl.getDiscounts());
	}
	
	
	@GetMapping(value="/admin/discounts/{serviceName}/{discount}")
	public ResponseEntity<String> addDiscount(@PathVariable("serviceName") String serviceName,@PathVariable("discount") int discount) {
		if(discountbsl.addDiscount(serviceName, discount))
			return ResponseEntity.status(HttpStatus.CREATED).body("Discount Added Successfully");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error Service Not Found");
	}
}