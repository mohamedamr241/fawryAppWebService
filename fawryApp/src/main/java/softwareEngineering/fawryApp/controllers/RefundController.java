package softwareEngineering.fawryApp.controllers;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import softwareEngineering.fawryApp.bsl.RefundBsl;
import softwareEngineering.fawryApp.bsl.TimeStampBsl;
import softwareEngineering.fawryApp.models.TransactionEntity;


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
		if(TimeStampBsl.checkValidation(obj.timeStamp,obj.email)) {
			return refundBsl.requestRefund(obj);			
		}
		return "you must signIn first";
	}
	
	@GetMapping(value = "/admin/refundRequests")
	public ArrayList<TransactionEntity> refundRequests() {
		return refundBsl.refund.getrefundRequestList();
	}
	
	@GetMapping(value = "/admin/refundTransactions")
	public ArrayList<TransactionEntity> refundTransaction() {
		return refundBsl.refund.getrefundedTransactions();
	}
	
	@GetMapping(value = "/admin/processRefund/{transId}")
	public String proccessRefund(@PathVariable("transId") int transId)
	{
		return refundBsl.processRefund(transId);
	}
	
	@GetMapping(value = "/admin/processRefund/{transId}/{decision}")
	public String accOrRej(@PathVariable("transId") int transId, @PathVariable("decision") String decision)
	{
		return refundBsl.accOrRej(decision, transId);
	}

}

