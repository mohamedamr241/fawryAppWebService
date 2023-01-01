package softwareEngineering.fawryApp.models;

public class Admin{
	private String email = "admin@gmail.com", password = "0000";
	public static String timeStamp="0";
	public String timestamp;
	
	public void setAdminPass(String password)
	{
		this.password = password;
	}
	
	
	public void setAdminEmail(String email)
	{
		this.email = email;
	}
	
	public String getAdminEmail()
	{
		return email;
	}
	
	public String getAdminPass()
	{
		return password;
	}



	

	
	

	
}
