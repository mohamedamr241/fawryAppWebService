package softwareEngineering.fawryApp.models;

import java.util.*;

import org.springframework.stereotype.Component;

import softwareEngineering.fawryApp.bsl.User;
import softwareEngineering.fawryApp.bsl.Wallet;
@Component
public class Account {
	public String email;
	public String userName;
	public String password;
	public static Map<String, String> userAccounts = new HashMap<String, String>();
	public static Map<String, User> users = new HashMap<String, User>();
	public static Map<String, Wallet> userWallet = new HashMap<String, Wallet>();
	public static String adminEmail = "admin@gmail.com", adminPass = "0000";
	
}
