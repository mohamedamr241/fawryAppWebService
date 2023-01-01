package softwareEngineering.fawryApp.controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import softwareEngineering.fawryApp.bsl.Services;

@RestController
public class ServiceController{
	
	@GetMapping(value="/services")
	public ResponseEntity<ArrayList<String>> services() {
		return ResponseEntity.ok(Services.displayServices());
	}

	
}

