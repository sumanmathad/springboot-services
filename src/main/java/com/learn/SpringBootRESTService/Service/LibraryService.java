package com.learn.SpringBootRESTService.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.SpringBootRESTService.Library;
import com.learn.SpringBootRESTService.repository.LibraryRepository;
@Service
public class LibraryService {
	@Autowired
	LibraryRepository libraryRepository;
	
	public String buildId(String isbn, int aisle) {
		return isbn+aisle;		
	}
	
	public boolean checkBookExists(String id) {
		
		Optional<Library> lib = libraryRepository.findById(id);
		if(lib.isPresent()) {
			return true;
		}
		else {
			return false;
		}
		
		
	}
	
	

}
