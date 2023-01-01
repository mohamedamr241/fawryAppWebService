package softwareEngineering.fawryApp.wallet.controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.admin.models.Admin;
import softwareEngineering.fawryApp.timeStampBsl.TimeStampBsl;
import softwareEngineering.fawryApp.user.models.Account;
import softwareEngineering.fawryApp.wallet.bsl.Wallet;



@RestController
public class WalletController{
	
	@PostMapping(value="/user/wallet/charge/{chargeAmount}")
	public ResponseEntity<String> chargeWallet(@PathVariable("chargeAmount") double chargeAmount, @RequestBody Account account) {
		if(TimeStampBsl.checkValidation(account.gettimeStamp(),account.getEmail())) {
			Wallet userWallet = Wallet.getUserWallet(account.getEmail());
			return ResponseEntity.ok(userWallet.chargeViaCreditCard(account.getEmail(), chargeAmount));			
		}
		else {
			return ResponseEntity.ok("you must signIn first");
		}
	}
	
	@PostMapping(value="/admin/WalletTransactions")
	public ResponseEntity<ArrayList<String>> WalletTransactions(@RequestBody Admin ad) {
		if(TimeStampBsl.checkValidationAdmin(ad.timeStamp)) 
			return ResponseEntity.ok(Wallet.getAddtoWalletTrans());
		return null;
		
	}
}














