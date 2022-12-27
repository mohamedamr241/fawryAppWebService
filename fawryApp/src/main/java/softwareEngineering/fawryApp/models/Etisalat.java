package softwareEngineering.fawryApp.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Etisalat")
public class Etisalat extends ServiceProviders{
	public int mobileNumber;
	public int amount;
//	@Override
//	protected void setDataForm() {
//		serviceProviderForm.add("Mobile Number");
//		serviceProviderForm.add("Amount");		
//	}
}
