package softwareEngineering.fawryApp.models;

import softwareEngineering.fawryApp.bsl.Wallet;
import java.util.*;

public class Account{
	private String email, username, password;
	private Wallet wallet;
	private boolean isFirstTrans = true;
	private ArrayList<String> notifications = new ArrayList<String>();
	
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Wallet getWallet() {
        return wallet;
    }
    public void createWallet() {
        wallet = new Wallet();
    }
    public boolean isFirstTrans() {
        return isFirstTrans;
    }
    public void setFirstTrans(boolean isFirstTrans) {
        this.isFirstTrans = isFirstTrans;
    }
    public ArrayList<String> getNotifications() {
        return notifications;
    }
    public void addNotification(String notification) {
        notifications.add(notification);
    }	
}
