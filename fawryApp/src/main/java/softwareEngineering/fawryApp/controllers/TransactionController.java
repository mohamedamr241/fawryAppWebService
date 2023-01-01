package softwareEngineering.fawryApp.controllers;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.bsl.PaymentBsl;
import softwareEngineering.fawryApp.bsl.TimeStampBsl;
import softwareEngineering.fawryApp.bsl.TransactionBsl;
import softwareEngineering.fawryApp.models.Admin;
import softwareEngineering.fawryApp.models.TransactionEntity;
import softwareEngineering.fawryApp.models.Transactions;

@RestController
public class TransactionController{

	private TransactionBsl transbsl;
	
	public TransactionController(TransactionBsl transbsl)
	{
		this.transbsl = transbsl;
	}
	
	@PostMapping(value="/services/{serviceName}/{serviceProvider}/{paymentMethod}")
	public ResponseEntity<Map<String, String>> createTransaction(@PathVariable("serviceName") String serviceName,@PathVariable("serviceProvider") String serviceProvider, @PathVariable("paymentMethod") String paymentMethod, @RequestBody PaymentBsl payment) {
			Map<String, String> details = transbsl.createTransaction(serviceName, serviceProvider, paymentMethod, payment);
			if(details.size() == 1)
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(details);
			return ResponseEntity.ok(details);

		}
	
	@PostMapping(value="/admin/PaymentTransactions")
	public ResponseEntity<ArrayList<TransactionEntity>> paymentTransactions(@RequestBody Admin ad) {
		if(TimeStampBsl.checkValidationAdmin(ad.timestamp))
			return ResponseEntity.ok(Transactions.getTransactions());
		return ResponseEntity.ok(null);
	}
}
 