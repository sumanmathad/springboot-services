package com.learn.SpringBootRESTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.SpringBootRESTService.repository.LibraryRepository;

@RestController
public class LibraryController {

	@Autowired
	LibraryRepository repository;
	
	@PostMapping("/addBook")
	public void addBookImplementation(@RequestBody Library library) {
		library.setId(library.getIsbn()+library.getAisle());
		repository.save(library);
	}
}
