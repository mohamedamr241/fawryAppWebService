package softwareEngineering.fawryApp.admin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import softwareEngineering.fawryApp.admin.bsl.AdminBsl;
import softwareEngineering.fawryApp.admin.models.Admin;


@RestController
public class AdminController{
	private AdminBsl adminBsl;
	
	public AdminController(AdminBsl adminBsl)
	{
		this.adminBsl = adminBsl;
	}
	
	@PostMapping(value="/admin/signIn")
	public ResponseEntity<String> signIn(@RequestBody Admin ad) {
		return ResponseEntity.ok(adminBsl.signIn(ad));
	}
	
	@PostMapping(value="/admin/signOut")
	public String signOut(@RequestBody Admin ad) {
		return adminBsl.signOut(ad.timestamp);
	}
}

