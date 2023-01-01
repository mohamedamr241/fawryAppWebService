package softwareEngineering.fawryApp.admin.bsl;

import org.springframework.stereotype.Service;


import softwareEngineering.fawryApp.admin.models.Admin;
import softwareEngineering.fawryApp.timeStampBsl.TimeStampBsl;



@Service
public class AdminBsl{

	private Admin admin = new Admin();
	public String signIn(Admin ad)
	{
		if(ad.getAdminEmail().equals(admin.getAdminEmail()) && ad.getAdminPass().equals(admin.getAdminPass()))
			if(Admin.timeStamp.equals("0")) {
				String timeStamp=TimeStampBsl.timeStampCreation();
				Admin.timeStamp=timeStamp;
				return "logged in successfully, your timeStamp is "+timeStamp;
			}
			else {
				return "you already signedIn";
			}
		return "Email or password is invalid";
	}
	public String signOut(String timeStamp) {
		if(Admin.timeStamp.equals(timeStamp)) {
			Admin.timeStamp="0";
			return "logged out successfully";
		}
		return "you already logged out";
	}
}