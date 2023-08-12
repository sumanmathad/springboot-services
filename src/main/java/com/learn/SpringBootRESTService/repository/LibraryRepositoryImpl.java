package com.learn.SpringBootRESTService.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.learn.SpringBootRESTService.Library;

public class LibraryRepositoryImpl implements LibraryRepositoryCustom {
	@Lazy
	@Autowired
	LibraryRepository repository;
	
	@Override
	public List<Library> findAllByAuthor(String authorName) {
		List<Library> booksWithAuthor= new ArrayList<Library>();
		List<Library> books= repository.findAll();
		
		for(Library book: books) {
			if (book.getAuthor().equalsIgnoreCase(authorName)) {
				booksWithAuthor.add(book);
			}
		}
		return booksWithAuthor;
	}

}
