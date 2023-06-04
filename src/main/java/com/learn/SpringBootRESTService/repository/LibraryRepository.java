package com.learn.SpringBootRESTService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.SpringBootRESTService.Library;



public interface LibraryRepository extends JpaRepository<Library,String> {

}
