package softwareEngineering.fawryApp.bsl;

import org.springframework.stereotype.Component;

@Component
public interface Subject {
	public void Notify(String note);
	public void subscribe(Observer ob);
	
}
