package softwareEngineering.fawryApp.bsl;

import org.springframework.stereotype.Service;

@Service
public interface Observer {
	public void update(String note);
}
