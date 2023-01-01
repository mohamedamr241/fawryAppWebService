package softwareEngineering.fawryApp.bsl;

import org.springframework.stereotype.Service;


import softwareEngineering.fawryApp.models.Admin;



@Service
public class AdminBsl{

	private Admin admin = new Admin();
	public String signIn(Admin ad)
	{
		if(ad.email.equals(admin.email) && ad.password.equals(admin.password))
			if(Admin.timeStamp.equals("0")) {
				String timeStamp=TimeStampBsl.timeStampCreation();
				Admin.timeStamp=timeStamp;
				return "your timeStamp is "+timeStamp;
			}
			else {
				return "you already signedIn";
			}
		return "Email or password incorrent";
	}
	public String signOut(String timeStamp) {
		if(Admin.timeStamp.equals(timeStamp)) {
			Admin.timeStamp="0";
			return "logged out successfully";
		}
		return "you already logged out";
	}
}