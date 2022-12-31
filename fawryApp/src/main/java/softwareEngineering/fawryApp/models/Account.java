package softwareEngineering.fawryApp.models;

import softwareEngineering.fawryApp.bsl.Wallet;
import java.util.*;

public class Account{
	public String email, username, password, CCN, creditCardNum;
	public Wallet wallet;
	public boolean isFirstTrans = true;
	public ArrayList<String> notifications = new ArrayList<String>();
	
	//setters and getters
	
}
