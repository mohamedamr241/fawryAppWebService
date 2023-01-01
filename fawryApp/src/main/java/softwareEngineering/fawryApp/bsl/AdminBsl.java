package softwareEngineering.fawryApp.bsl;

import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.models.Admin;


@Service
public class AdminBsl{

	private Admin admin = new Admin();
	public  boolean signIn(Admin ad)
	{
		if(ad.getAdminEmail().equals(admin.getAdminEmail()) && ad.getAdminPass().equals(admin.getAdminPass()))
			return true;
		return false;
	}
}