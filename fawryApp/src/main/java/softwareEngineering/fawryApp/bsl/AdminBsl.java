package softwareEngineering.fawryApp.bsl;

import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.models.Admin;


@Service
public class AdminBsl{

	private Admin admin = new Admin();
	public String signIn(Admin ad)
	{
		if(ad.email.equals(admin.email) && ad.password.equals(admin.password))
			return "logged in successfully";
		return "Email or password incorrent";
	}
}