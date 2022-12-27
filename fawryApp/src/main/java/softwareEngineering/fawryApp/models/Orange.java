package softwareEngineering.fawryApp.models;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Orange")
public class Orange extends ServiceProviders{
	public int mobileNumber;
	public int amount;
//	@Override
//	protected void setDataForm() {
//		serviceProviderForm.add("Mobile Number");
//		serviceProviderForm.add("Amount");			
//	}

}
