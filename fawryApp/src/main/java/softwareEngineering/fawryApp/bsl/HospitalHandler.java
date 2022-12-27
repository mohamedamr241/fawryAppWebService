package softwareEngineering.fawryApp.bsl;
import java.util.ArrayList;

public class HospitalHandler implements Handler {

	@Override
	public boolean execute(ArrayList<String> providerForm) {
		boolean checkAmout=false;
		String amount=providerForm.get(1);
	
		if(!amount.equals("0"))
			checkAmout=true;
		return checkAmout;
	
	}

}
