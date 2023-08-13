package com.learn.SpringBootRESTService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.learn.SpringBootRESTService.Service.LibraryService;
import com.learn.SpringBootRESTService.repository.LibraryRepository;

@RestController
public class LibraryController {

	@Autowired
	LibraryRepository repository;
	
	@Autowired
	AddResponse addResponse;
	
	@Autowired
	LibraryService libraryService;
	
	@PostMapping("/addBook")
	public ResponseEntity addBookImplementation(@RequestBody Library library) {
		String id= libraryService.buildId(library.getIsbn(),library.getAisle());
		
		if (!libraryService.checkBookExists(id)) {
		library.setId(id);
		repository.save(library);
		addResponse.setMsg("Success!! Book is added");
		addResponse.setId(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Unique-Header", "First");
		//return addResponse; without response headers!
		return new ResponseEntity<AddResponse>(addResponse,headers,HttpStatus.CREATED);
		}
		else 
		{
			addResponse.setMsg("Book Already Exists");
			addResponse.setId(id);
			
			return new ResponseEntity<AddResponse>(addResponse,HttpStatus.ACCEPTED);
		}
		
	}
	
	@GetMapping("/getBooks/{id}")
	public Library getBookById(@PathVariable(value = "id")String id) {
		try{
			Library lib = repository.findById(id).get();
		
		return lib;
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/getBooks/author")
	public List<Library> getBookByAuthorName(@RequestParam(value = "authorname")String authorname) {
		return repository.findAllByAuthor(authorname);
	}
	
	@PutMapping("/updateBook/{id}")
	public ResponseEntity<Library> updateBook(@PathVariable (value ="id")String id, @RequestBody Library library) {
		Library existingBook = repository.findById(id).get();
		existingBook.setAisle(library.getAisle());
		existingBook.setBook_name(library.getBook_name());
		existingBook.setAuthor(library.getAuthor());
		repository.save(existingBook);
		
		return new ResponseEntity<Library>(existingBook,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteBook")
	public ResponseEntity deleteBookByID(@RequestBody Library library){
		Library libToDelete	= repository.findById(library.getId()).get();
			repository.delete(libToDelete);
			
		return new ResponseEntity<>("Book is deleted",HttpStatus.CREATED);
	}
	
	
	
	
}
