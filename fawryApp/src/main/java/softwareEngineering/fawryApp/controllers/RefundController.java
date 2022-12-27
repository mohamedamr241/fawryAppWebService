package softwareEngineering.fawryApp.controllers;


import java.util.ArrayList;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.bsl.Admin;
import softwareEngineering.fawryApp.bsl.Services;
import softwareEngineering.fawryApp.bsl.User;
import softwareEngineering.fawryApp.models.Account;
import softwareEngineering.fawryApp.models.TransactionEntity;
import softwareEngineering.fawryApp.models.Transactions;
@RestController
public class RefundController{

	Admin admin = new Admin();
	User user = new User();
	@PostMapping(value = "/user/requestRefund")
	public String requestRefund(@RequestBody TransactionEntity obj) 
	{
		return user.requestRefund(obj);
	}
	
	@GetMapping(value = "/admin/refundRequests")
	public ArrayList<TransactionEntity> refundRequests() {
		return admin.getrefundRequestList();
	}
	
	@PostMapping(value = "/admin/processRefund/{transId}")
	public String proccessRefund(@PathVariable("transId") int transId)
	{
		if(admin.processRefund(transId))
			return "Refund request with id " + transId + " is correct";
		return "Refund request with id " + transId + " is incorrect";
	}
	
	@PostMapping(value = "/admin/accOrRej/{transId}/{decision}")
	public String accOrRej(@PathVariable("transId") int transId, @PathVariable("decision") String decision)
	{
		return admin.accOrRej(decision, transId);
	}

}

