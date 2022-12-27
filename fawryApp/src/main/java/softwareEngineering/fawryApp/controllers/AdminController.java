package softwareEngineering.fawryApp.controllers;


import java.util.ArrayList;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.bsl.Admin;
import softwareEngineering.fawryApp.bsl.Wallet;
import softwareEngineering.fawryApp.models.Account;
import softwareEngineering.fawryApp.models.TransactionEntity;
import softwareEngineering.fawryApp.models.Transactions;
@RestController
public class AdminController{
	private Admin admin = new Admin();
	@RequestMapping(value="/admin/signIn",method = RequestMethod.POST)
	public String signIn(@RequestBody Account acc) {
		return admin.signIn(acc.email, acc.password);
	}
	
	@RequestMapping(value="/admin/PaymentTransactions",method = RequestMethod.GET)
	public ArrayList<TransactionEntity> paymentTransactions() {
		return Transactions.transactions;
	}
	@RequestMapping(value="/admin/WalletTransactions",method = RequestMethod.GET)
	public Map<String, Wallet> WalletTransactions() {
		return Account.userWallet;
	}
}

