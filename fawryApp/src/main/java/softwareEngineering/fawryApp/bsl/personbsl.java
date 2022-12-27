package softwareEngineering.fawryApp.bsl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import softwareEngineering.fawryApp.models.Person;
@Service
public class personbsl{
	private final List<Person> persontable;
	public personbsl() {
		this.persontable=new ArrayList<>();
	}
	public String add(Person p) {
		for(Person s:persontable) {
			if(s.getId()==p.getId()) {
				return "this id is already exist";
			}
		}
		persontable.add(p);
		return "added successfully";
	}
}