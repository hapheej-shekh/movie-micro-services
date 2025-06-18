package com.starter.jpa.service.joins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.starter.jpa.service.beans.Response;
import com.starter.jpa.service.repositories.LibraryRepository;
import com.starter.jpa.service.repositories.StudentRepository;

@RestController
@RequestMapping("AllJoinTest")
public class AllJoinTestController {

	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private LibraryRepository libraryRepo;
	
	
	@RequestMapping("oneToOne")
	public ResponseEntity<Response> oneToOne(@RequestBody Student student) {
		
		
		Response response = new Response(HttpStatus.CREATED.toString(), 
				"One to one success...", studentRepo.save(student));
		
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping("oneToMany")
	public ResponseEntity<Response> oneToMany(@RequestBody Student student) {
		
		
		Response response = new Response(HttpStatus.CREATED.toString(), 
				"One to many success...", studentRepo.save(student));
		
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping("manyToOne")
	public ResponseEntity<Response> manyToOne(@RequestBody Library library) {
		
		
		Response response = new Response(HttpStatus.CREATED.toString(), 
				"Many to one success...", libraryRepo.save(library));
		
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping("manyToManyStudent")
	public ResponseEntity<Response> manyToManyStudent(@RequestBody Student student) {
		
		
		Response response = new Response(HttpStatus.CREATED.toString(), 
				"Many to many success...", studentRepo.save(student));
		
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping("manyToManyLibrary")
	public ResponseEntity<Response> manyToManyLibrary(@RequestBody Library library) {
		
		
		Response response = new Response(HttpStatus.CREATED.toString(), 
				"Many to many success...", libraryRepo.save(library));
		
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
}
