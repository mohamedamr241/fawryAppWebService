package softwareEngineering.fawryApp.controllers;



import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import jakarta.servlet.http.HttpServletRequest;
import softwareEngineering.fawryApp.bsl.Cash;
import softwareEngineering.fawryApp.bsl.CreditCard;
import softwareEngineering.fawryApp.bsl.DonationService;
import softwareEngineering.fawryApp.bsl.InternetService;
import softwareEngineering.fawryApp.bsl.LandlineService;
import softwareEngineering.fawryApp.bsl.MobileService;
import softwareEngineering.fawryApp.bsl.OverallDiscount;
import softwareEngineering.fawryApp.bsl.Payment;
import softwareEngineering.fawryApp.bsl.Services;
import softwareEngineering.fawryApp.bsl.SpecificDiscount;
import softwareEngineering.fawryApp.bsl.Wallet;
import softwareEngineering.fawryApp.bsl.personbsl;

import softwareEngineering.fawryApp.models.Account;
import softwareEngineering.fawryApp.models.Person;
import softwareEngineering.fawryApp.models.ServiceProviders;
import softwareEngineering.fawryApp.models.Transactions;
import softwareEngineering.fawryApp.models.We;
@RestController
public class serviceController{
	ServiceProviders servprovider;
	String UserEmail;
	int transactionCounter = 0;
	static int counter = 0;
	double disPrice;
	@RequestMapping(value="/user/services",method = RequestMethod.GET)
	public ArrayList<String> services() {
		return Services.services;
	}
	@RequestMapping(value="/user/services/search/{serviceName}",method = RequestMethod.GET)
	public ArrayList<String> search(@PathVariable("serviceName") String serviceName){
		if(serviceName.equals("mobileRecharge")) {
			Services mobile = new MobileService();
			return mobile.displayProviders();
		}
		else if(serviceName.equals("Landline")) {
			Services landline = new LandlineService();
			return landline.displayProviders();
		}
		else if(serviceName.equals("InternetPayment")) {
			Services Internet = new InternetService();
			return Internet.displayProviders();
		}
		else if(serviceName.equals("Donations")) {
			Services donation = new DonationService();
			return donation.displayProviders();
		}
		return null;
	}
	@RequestMapping(value="/user/services/create/{serviceName}/{serviceProvider}/{paymentMethod}",method = RequestMethod.POST)
	public Map<String,String> create(@PathVariable("serviceName") String serviceName,@PathVariable("serviceProvider") String serviceProvider,@PathVariable("paymentMethod") String paymentMethod,HttpServletRequest request) {
		String body;
		double price=0;
		JsonNode json;
		String mobileNumber="";
		String amount="";
		String creditCardNum="";
		String CCN="" ;
		Map<String,String> map=new HashMap<String,String>();
		try {
			body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			ObjectMapper mapper = new JsonMapper();
		    json = mapper.readTree(body);
		    if(serviceProvider.equals("We") || serviceProvider.equals("Etisalat") ||serviceProvider.equals("Orange") || serviceProvider.equals("Vodafone")) {
		    	mobileNumber=json.get("mobileNumber").asText();
		    	map.put("mobileNumber",mobileNumber);
		    }
		    amount=json.get("amount").asText();
		    UserEmail=json.get("email").asText();
		    map.put("amount",amount);
		    price= Double.valueOf(amount);
		    if(paymentMethod.equals("creditCard")) {
		    	creditCardNum=json.get("creditCardNum").asText();
		    	CCN=json.get("CCN").asText();
		    	map.put("creditCardNum",creditCardNum);
		    	map.put("CCN",CCN);
		    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(serviceName.equals("mobileRecharge")) {
			if(!serviceProvider.equals("Orange")|| !serviceProvider.equals("Etisalat") || !serviceProvider.equals("We") || !serviceProvider.equals("Vodafone")) {
				map.put("ERROR","this service provider is not available");
				return map;
			}
			Services mobile = new MobileService();
			servprovider=mobile.orderServiceProvider(serviceProvider);
			ArrayList<String> formAns = new ArrayList<String>(); //to store user's answers to the form
			formAns.add(mobileNumber);
			formAns.add(amount);
			boolean check =mobile.providerHandler.execute(formAns);
			if(check) {
				if(paymentMethod.equals("creditCard")) //credit card
				{
					Payment payMethod = new CreditCard();
					if(transactionCounter == 0) {
						payMethod = new OverallDiscount(payMethod);
						map.put("Overall discount","Overall discount will be preformed as it's your first transaction (10% off)");
					}
					boolean serviceDiscount = SpecificDiscount.searchService(serviceName);
					if(serviceDiscount)
						payMethod = new SpecificDiscount(payMethod);							
					mobile.setPayMethod(payMethod);	
					disPrice = mobile.performPayMethod(price);
					map.put("price after discount",Double.toString(disPrice));
					map.put("payment status","payment with credit card is done successfully");
					transactionCounter++;
					counter++;
					Transactions.addTransaction(counter, disPrice, serviceName, UserEmail);
					map.put("transaction ID","Your transaction id is " + counter + "(must be known so you can request rufund)");
					
				}
				else if(paymentMethod.equals("cash"))//cash
				{
					Payment payMethod = new Cash();
					if(transactionCounter == 0) {
						map.put("Overall discount","Overall discount will be preformed as it's your first transaction (10% off)");
						payMethod = new OverallDiscount(payMethod);
					}
					
					boolean serviceDiscount = SpecificDiscount.searchService(serviceName);
					if(serviceDiscount)
						payMethod = new SpecificDiscount(payMethod);							
					mobile.setPayMethod(payMethod);	
					disPrice = mobile.performPayMethod(price);
					map.put("price after discount",Double.toString(disPrice));
					map.put("payment status","payment with cash is done successfully");
					transactionCounter++;
					counter++;
					Transactions.addTransaction(counter, disPrice, serviceName, UserEmail);
					map.put("transaction ID","Your transaction id is " + counter + "(must be known so you can request rufund)");
				}
				else if (paymentMethod.equals("wallet")){ //wallet
					Wallet userWallet = Wallet.getUserWallet(UserEmail);
					if(transactionCounter == 0) {
						Payment discount = new OverallDiscount(userWallet);
						
						boolean serviceDiscount = SpecificDiscount.searchService(serviceName);
						if(serviceDiscount)
						{
							discount = new SpecificDiscount(discount);
						}
						mobile.setPayMethod(discount);
					}
					else {
						boolean serviceDiscount = SpecificDiscount.searchService(serviceName);
						if(serviceDiscount)
						{
							Payment discount = new SpecificDiscount(userWallet);
							mobile.setPayMethod(discount);

						}
						else {
							mobile.setPayMethod(userWallet);								
						}
					}
					if(userWallet.getBalance()>=price) {
						if(transactionCounter == 0)
							map.put("Overall discount","Overall discount will be preformed as it's your first transaction (10% off)");
						disPrice = mobile.performPayMethod(price);
						map.put("price after discount",Double.toString(disPrice));
						map.put("payment status","payment with wallet is done successfully");
						map.put("walletBalance",Double.toString(userWallet.getBalance()));
						transactionCounter++;
						counter++;
						Transactions.addTransaction(counter, disPrice, serviceName, UserEmail);
						map.put("transaction ID","Your transaction id is " + counter + "(must be known so you can request rufund)");
						check=false;
					}
					else {
						map.put("Error","Your wallet balance is not enough");
						return map;
					} 
				}else {
					map.put("Error","this payment method is not available");
					return map;
				}
			}
			else {
				map.put("user input error","you entered wrong mobileNumber or amount");
				return map;
			}
		}
		if(serviceName.equals("Landline")){
			if(!serviceProvider.equals("MonthlyReceipt")|| !serviceProvider.equals("QuarterReceipt")) {
				map.put("ERROR","this service provider is not available");
				return map;
			}
			Services landline = new LandlineService();
			servprovider=landline.orderServiceProvider(serviceName);
			if(paymentMethod.equals("creditCard")) //credit card
			{
				Payment payMethod = new Cash();
				if(transactionCounter == 0) {
					map.put("Overall discount","Overall discount will be preformed as it's your first transaction (10% off)");
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serviceName);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				landline.setPayMethod(payMethod);	
				disPrice = landline.performPayMethod(price);
				map.put("price after discount",Double.toString(disPrice));
				map.put("payment status","payment with cash is done successfully");
				transactionCounter++;
				counter++;
				Transactions.addTransaction(counter, disPrice, serviceName, UserEmail);
				map.put("transaction ID","Your transaction id is " + counter + "(must be known so you can request rufund)");
			}
			else if(paymentMethod.equals("cash"))//cash
			{
				Payment payMethod = new Cash();
				if(transactionCounter == 0) {
					map.put("Overall discount","Overall discount will be preformed as it's your first transaction (10% off)");
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serviceName);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				landline.setPayMethod(payMethod);	
				disPrice = landline.performPayMethod(price);
				map.put("price after discount",Double.toString(disPrice));
				map.put("payment status","payment with cash is done successfully");
				transactionCounter++;
				counter++;
				Transactions.addTransaction(counter, disPrice, serviceName, UserEmail);
				map.put("transaction ID","Your transaction id is " + counter + "(must be known so you can request rufund)");
			}
			else {
				map.put("Error","this payment method is not available");
				return map;
			}

		}
		if(serviceName.equals("InternetPayment")){
			if(!serviceProvider.equals("Orange")|| !serviceProvider.equals("Etisalat") || !serviceProvider.equals("We") || !serviceProvider.equals("Vodafone")) {
				map.put("ERROR","this service provider is not available");
				return map;
			}
			Services Internet = new InternetService();
			servprovider=Internet.orderServiceProvider(serviceName);
			if(paymentMethod.equals("creditCard")) //credit card
			{
				Payment payMethod = new Cash();
				if(transactionCounter == 0) {
					map.put("Overall discount","Overall discount will be preformed as it's your first transaction (10% off)");
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serviceName);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				Internet.setPayMethod(payMethod);	
				disPrice = Internet.performPayMethod(price);
				map.put("price after discount",Double.toString(disPrice));
				map.put("payment status","payment with cash is done successfully");
				transactionCounter++;
				counter++;
				Transactions.addTransaction(counter, disPrice, serviceName, UserEmail);
				map.put("transaction ID","Your transaction id is " + counter + "(must be known so you can request rufund)");
			}
			else if(paymentMethod.equals("cash"))//cash
			{
				Payment payMethod = new Cash();
				if(transactionCounter == 0) {
					map.put("Overall discount","Overall discount will be preformed as it's your first transaction (10% off)");
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serviceName);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				Internet.setPayMethod(payMethod);	
				disPrice = Internet.performPayMethod(price);
				map.put("price after discount",Double.toString(disPrice));
				map.put("payment status","payment with cash is done successfully");
				transactionCounter++;
				counter++;
				Transactions.addTransaction(counter, disPrice, serviceName, UserEmail);
				map.put("transaction ID","Your transaction id is " + counter + "(must be known so you can request rufund)");
			}
			else {
				map.put("Error","this payment method is not available");
				return map;
			}
		}
		if(serviceName.equals("Donations")){
			if(!serviceProvider.equals("CancerHospital")|| !serviceProvider.equals("Schools") || !serviceProvider.equals("NGOs")) {
				map.put("ERROR","this service provider is not available");
				return map;
			}
			Services donation = new DonationService();
			servprovider=donation.orderServiceProvider(serviceName);
			if(paymentMethod.equals("creditCard")) //credit card
			{
				Payment payMethod = new Cash();
				if(transactionCounter == 0) {
					map.put("Overall discount","Overall discount will be preformed as it's your first transaction (10% off)");
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serviceName);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				donation.setPayMethod(payMethod);	
				disPrice = donation.performPayMethod(price);
				map.put("price after discount",Double.toString(disPrice));
				map.put("payment status","payment with cash is done successfully");
				transactionCounter++;
				counter++;
				Transactions.addTransaction(counter, disPrice, serviceName, UserEmail);
				map.put("transaction ID","Your transaction id is " + counter + "(must be known so you can request rufund)");
			}
			else if(paymentMethod.equals("cash"))//cash
			{
				Payment payMethod = new Cash();
				if(transactionCounter == 0) {
					map.put("Overall discount","Overall discount will be preformed as it's your first transaction (10% off)");
					payMethod = new OverallDiscount(payMethod);
				}
				
				boolean serviceDiscount = SpecificDiscount.searchService(serviceName);
				if(serviceDiscount)
					payMethod = new SpecificDiscount(payMethod);							
				donation.setPayMethod(payMethod);	
				disPrice = donation.performPayMethod(price);
				map.put("price after discount",Double.toString(disPrice));
				map.put("payment status","payment with cash is done successfully");
				transactionCounter++;
				counter++;
				Transactions.addTransaction(counter, disPrice, serviceName, UserEmail);
				map.put("transaction ID","Your transaction id is " + counter + "(must be known so you can request rufund)");
			}
			else {
				map.put("Error","this payment method is not available");
				return map;
			}
		}
		return map;
	}
}

