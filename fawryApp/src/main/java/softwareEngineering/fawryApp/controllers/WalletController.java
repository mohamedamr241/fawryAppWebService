package softwareEngineering.fawryApp.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.bsl.TimeStampBsl;
import softwareEngineering.fawryApp.bsl.Wallet;
import softwareEngineering.fawryApp.models.Account;
import softwareEngineering.fawryApp.models.Admin;
import softwareEngineering.fawryApp.models.TransactionEntity;
import softwareEngineering.fawryApp.models.Transactions;

@RestController
public class WalletController{
	
	@PostMapping(value="/user/wallet/charge/{chargeAmount}")
	public String chargeWallet(@PathVariable("chargeAmount") double chargeAmount, @RequestBody Account account) {
		if(TimeStampBsl.checkValidation(account.timeStamp,account.email)) {
			Wallet userWallet = Wallet.getUserWallet(account.email);
			return userWallet.chargeViaCreditCard(chargeAmount);			
		}
		else {
			return "you must signIn first";
		}
	}
	
	@GetMapping(value="/admin/WalletTransactions")
	public ArrayList<TransactionEntity> WalletTransactions(@RequestBody Admin ad) {
		if(TimeStampBsl.checkValidationAdmin(ad.timestamp)) {
			return Transactions.getWalletTransactions();			
		}
		return null;
	}
}














