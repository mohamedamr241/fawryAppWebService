package softwareEngineering.fawryApp.refund.controllers;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.admin.models.Admin;
import softwareEngineering.fawryApp.refund.bsl.RefundBsl;
import softwareEngineering.fawryApp.timeStampBsl.TimeStampBsl;
import softwareEngineering.fawryApp.transaction.models.TransactionEntity;



@RestController
public class RefundController{

	private RefundBsl refundBsl;
	
	public RefundController(RefundBsl refundBsl)
	{
		this.refundBsl = refundBsl;
	}
	
	
	@PostMapping(value = "/user/requestRefund")
	public String requestRefund(@RequestBody TransactionEntity obj) 
	{
		if(TimeStampBsl.checkValidation(obj.getTimeStamp(),obj.getEmail())) {
			return refundBsl.requestRefund(obj);			
		}
		return "you must signIn first";
	}
	
	@GetMapping(value = "/admin/refundRequests")
	public ArrayList<TransactionEntity> refundRequests(@RequestBody Admin  ad) {
		if(TimeStampBsl.checkValidationAdmin(ad.timestamp)) {
			return refundBsl.refund.getrefundRequestList();			
		}
		return null;
	}
	
	@GetMapping(value = "/admin/refundTransactions")
	public ArrayList<TransactionEntity> refundTransaction(@RequestBody Admin  ad) {
		if(TimeStampBsl.checkValidationAdmin(ad.timestamp)) {
			return refundBsl.refund.getrefundedTransactions();
		}
		return null;
	}
	
	@GetMapping(value = "/admin/processRefund/{transId}")
	public String proccessRefund(@PathVariable("transId") int transId,@RequestBody Admin  ad)
	{
		if(TimeStampBsl.checkValidationAdmin(ad.timestamp)) {
			return refundBsl.processRefund(transId);
		}
		return "you must signIn first";
	}
	
	@GetMapping(value = "/admin/processRefund/{transId}/{decision}")
	public String accOrRej(@PathVariable("transId") int transId, @PathVariable("decision") String decision,@RequestBody Admin  ad)
	{
		if(TimeStampBsl.checkValidationAdmin(ad.timestamp)) {
			return refundBsl.accOrRej(decision, transId);
		}
		return "you must signIn first";
	}

}

