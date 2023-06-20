package com.example.libraryadmin.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.libraryadmin.dto.AssignBookDTO;
import com.example.libraryadmin.dto.BookDTO;
import com.example.libraryadmin.dto.UserDTO;
import com.example.libraryadmin.services.LibraryAdminService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/admin/")
public class LibraryAdminController {
	
	private LibraryAdminService adminService;

	Logger logger = LoggerFactory.getLogger(LibraryAdminController.class);
	
	private final WebClient client;
	
	@Autowired
	public LibraryAdminController( WebClient client,
			LibraryAdminService adminService){
		this.adminService = adminService;
		this.client = client;
	}
	
	@GetMapping("/test2")
	public ResponseEntity test2() {
		return ResponseEntity.ok("Working Simple");
	}
	
	@GetMapping("/")
	@ResponseBody
	public String LibraryAdminService() {
		return "LibraryAdminService";
	}


	
	
	
	@GetMapping("/test")
	public String test() {		
		return "Working" + 
				client
				.get()
				.uri("http://USERS/api/user/test")
				.retrieve()
				.bodyToFlux(String.class);
	}

	
	@PostMapping("/adduser")
	@ResponseBody
	public ResponseEntity createUser(@RequestBody UserDTO userDTO) {
		
		Optional<UserDTO> response =  this.adminService.createUser(userDTO);
		if(response.isPresent()) {
			return ResponseEntity.ok(response.get());
		}
		return ResponseEntity.badRequest().body("Request Cannot Process");
				
	}
	
	@GetMapping("/getalluser")
	public ResponseEntity getAllUser() {	
		return ResponseEntity.ofNullable(this.adminService.getAllUsers());
	}

	@PostMapping("/book/add")
	@ResponseBody
	public ResponseEntity addBook(@RequestBody BookDTO book) {
		return ResponseEntity.ok(this.adminService.addBookToLibrary(book));
	}
	
	@PostMapping("/books/add")
	@ResponseBody
	public ResponseEntity addBook(@RequestBody List<BookDTO> book) {
		logger.info("LIBRARY ENDPOINT HIT");
		return ResponseEntity.ok(this.adminService.addBooksToDatabase(book));
	}
	
	@GetMapping("/books/getall")
	@ResponseBody
	public ResponseEntity getAllBooks() {
		System.out.println("GETALLBOOKS");
		return ResponseEntity.ok(this.adminService.getAllBooks());
	}
	
	
	@GetMapping("/books/get/name={bookname}")
	@ResponseBody
	public ResponseEntity getBooksByBookName(@PathVariable String bookname
			) {
		System.out.println("GETALLBOOKS");
		return ResponseEntity.ok(this.adminService.getBooksByBookName(bookname));
	}
	
	@GetMapping("/books/get/author={author}")
	@ResponseBody
	public ResponseEntity getBooksByAuthor(@PathVariable String author
			) {
		System.out.println("GETALLBOOKS");
		return ResponseEntity.ok(this.adminService.getBooksByAuthor(author));
	}
	
	@PostMapping("/book/assign")
	@ResponseBody
	public ResponseEntity assignBookToUser(@RequestBody AssignBookDTO assignBookDTO) {		
		return ResponseEntity.ok(this.adminService.assignBookToUser(assignBookDTO));
	}
	
	
	
}
