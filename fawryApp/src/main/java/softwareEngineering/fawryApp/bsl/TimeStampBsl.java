package softwareEngineering.fawryApp.bsl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.Map;

import softwareEngineering.fawryApp.models.Account;
import softwareEngineering.fawryApp.models.User;

public class TimeStampBsl{
	public static boolean checkValidation(String time,String email) {
		for(Account acc: User.getAccounts()) {
			if(acc.email.equals(email) && acc.timeStamp.equals(time)) {
				return true;
			}
		}
		return false;
	}
	public static String timeStampCreation() {
		LocalDateTime now = LocalDateTime.now();
		int hour = now.getHour();
		int minute = now.getMinute();
		int second = now.getSecond();
		int millis = now.get(ChronoField.MILLI_OF_SECOND); 
		String timeStamp=Integer.toString(hour)+":"+Integer.toString(minute)+":"+Integer.toString(second)+":"+Integer.toString(millis);
		return timeStamp;
	}	
}