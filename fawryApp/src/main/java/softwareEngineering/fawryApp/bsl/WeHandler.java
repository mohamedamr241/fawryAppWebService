package softwareEngineering.fawryApp.bsl;

import java.util.ArrayList;

public class WeHandler implements Handler{
	@Override
	public boolean execute(ArrayList<String> providerForm) {
		boolean checkNumber=false;
		boolean checkAmout=false;
		String number=providerForm.get(0);
		String amount=providerForm.get(1);
		if(number.length()==10) {
			if((number.charAt(0)) == ('1')) {
				if((number.charAt(1)) == ('5')) {
					checkNumber=true;
				}
			}		
		}
		if(!amount.equals("0"))
			checkAmout=true;
		return checkNumber && checkAmout;
	}
}
