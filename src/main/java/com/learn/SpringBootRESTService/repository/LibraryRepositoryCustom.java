package com.learn.SpringBootRESTService.repository;

import java.util.List;

import com.learn.SpringBootRESTService.Library;

public interface LibraryRepositoryCustom {
	List<Library> findAllByAuthor(String authorName);

}
