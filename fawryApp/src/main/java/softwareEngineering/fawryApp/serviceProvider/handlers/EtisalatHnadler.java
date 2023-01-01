package softwareEngineering.fawryApp.serviceProvider.handlers;

import java.util.ArrayList;

public class EtisalatHnadler implements Handler{
	@Override
	public boolean execute(ArrayList<String> providerForm) {
		boolean checkNumber=false;
		boolean checkAmout=false;
		String number=providerForm.get(0);
		String amount=providerForm.get(1);
		if(number.length()==11) {
			if((number.charAt(0)) == ('0')) {
				if((number.charAt(1)) == ('1')) {
					if((number.charAt(2)) == ('1')) {
						checkNumber=true;
					}
				}
			}		
		}
		if(!amount.equals("0"))
			checkAmout=true;
		return checkNumber && checkAmout;
	}
}
