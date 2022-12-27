package softwareEngineering.fawryApp.models;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("We")
@Component
public class We extends ServiceProviders{
	public  int mobileNumber;
	public  int amount;
	public int getMobileNumber() {
		return mobileNumber;
	}
	public int getAmount() {
		return amount;
	}
}
