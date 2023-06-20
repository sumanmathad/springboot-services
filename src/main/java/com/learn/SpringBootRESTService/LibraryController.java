package com.learn.SpringBootRESTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.SpringBootRESTService.repository.LibraryRepository;

@RestController
public class LibraryController {

	@Autowired
	LibraryRepository repository;
	
	@Autowired
	AddResponse addResponse;
	
	
	@PostMapping("/addBook")
	public ResponseEntity addBookImplementation(@RequestBody Library library) {
		String id= library.getIsbn()+library.getAisle();
		library.setId(id);
		repository.save(library);
		addResponse.setMsg("Success!! Book is added");
		addResponse.setId(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Unique-Header", "First");
		//return addResponse; without response headers!
		return new ResponseEntity<AddResponse>(addResponse,headers,HttpStatus.CREATED);
		
	}
	
	
	
}
