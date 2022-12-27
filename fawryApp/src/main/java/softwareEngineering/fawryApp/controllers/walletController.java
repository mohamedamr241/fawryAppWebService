package softwareEngineering.fawryApp.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import jakarta.servlet.http.HttpServletRequest;
import softwareEngineering.fawryApp.bsl.Wallet;

@RestController
public class walletController{
	
	@RequestMapping(value="/user/wallet/charge/{chargeAmount}",method = RequestMethod.POST)
	public String chargeWallet(@PathVariable("chargeAmount") double chargeAmount,HttpServletRequest request) {
		String creditCardNum="";
		String CCN="" ;
		JsonNode json;
		String body;
		String UserEmail="";
		try {
			body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			ObjectMapper mapper = new JsonMapper();
		    json = mapper.readTree(body);
		    UserEmail=json.get("email").asText();
		    creditCardNum=json.get("creditCardNum").asText();
		    CCN=json.get("CCN").asText();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Wallet userWallet = Wallet.getUserWallet(UserEmail);
		String res=userWallet.chargeViaCreditCard(chargeAmount);
		return res;
	}
}