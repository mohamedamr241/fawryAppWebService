package softwareEngineering.fawryApp.controllers;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import softwareEngineering.fawryApp.bsl.RefundBsl;
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
		return refundBsl.requestRefund(obj);
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
	
	@GetMapping(value = "/admin/processRefund/{transId}/{decision}")//decision->accept, reject
	public String accOrRej(@PathVariable("transId") int transId, @PathVariable("decision") String decision)
	{
		return refundBsl.accOrRej(decision, transId);
	}

}

