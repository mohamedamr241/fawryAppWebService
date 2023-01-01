package softwareEngineering.fawryApp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import softwareEngineering.fawryApp.bsl.AdminBsl;
import softwareEngineering.fawryApp.models.Admin;

@RestController
public class AdminController{
	private AdminBsl adminBsl;
	
	public AdminController(AdminBsl adminBsl)
	{
		this.adminBsl = adminBsl;
	}
	
	@PostMapping(value="/admin/signIn")
	public ResponseEntity<String> signIn(@RequestBody Admin ad) {
		if(adminBsl.signIn(ad))
			return ResponseEntity.ok("logged in successfully");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email or password is invalid");
	}
	

}

