package softwareEngineering.fawryApp.controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.bsl.Wallet;
import softwareEngineering.fawryApp.models.Account;
import softwareEngineering.fawryApp.models.TransactionEntity;
import softwareEngineering.fawryApp.models.Transactions;

@RestController
public class WalletController{
	
	@PostMapping(value="/user/wallet/charge/{chargeAmount}")
	public String chargeWallet(@PathVariable("chargeAmount") double chargeAmount, @RequestBody Account account) {

		Wallet userWallet = Wallet.getUserWallet(account.getEmail());//timeStamp
		return userWallet.chargeViaCreditCard(chargeAmount);
	}
	
	@GetMapping(value="/admin/WalletTransactions")
	public ResponseEntity<ArrayList<TransactionEntity>> WalletTransactions() {
		return ResponseEntity.ok(Transactions.getWalletTransactions());
	}
}














