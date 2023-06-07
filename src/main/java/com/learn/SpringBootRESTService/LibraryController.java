package com.learn.SpringBootRESTService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

	
	@PostMapping("/addBook")
	public void addBookImplementation(@RequestBody Library library) {
		
	}
}
