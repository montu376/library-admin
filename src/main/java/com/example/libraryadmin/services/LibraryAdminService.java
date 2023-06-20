package com.example.libraryadmin.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.libraryadmin.dto.AssignBookDTO;
import com.example.libraryadmin.dto.BookDTO;
import com.example.libraryadmin.dto.BookListDTO;
import com.example.libraryadmin.dto.UserDTO;

import reactor.core.publisher.Mono;

@Service
public class LibraryAdminService implements IBookManagement {
	
	private final String USERHOST  = "http://localhost:8085/api/user/";
	private final String BOOKSHOST = "http://localhost:8085/api/book/";
	
	Logger logger = LoggerFactory.getLogger(LibraryAdminService.class);

	private RestTemplate restTemplate;
	
	@Autowired
	LibraryAdminService(RestTemplate restTemplate){
		this.restTemplate = restTemplate;	
	}

	public Optional<UserDTO> createUser(UserDTO user){
		ResponseEntity<UserDTO> userDTO = this.restTemplate
											  .postForEntity(USERHOST+"/add", user, UserDTO.class );
		return Optional.ofNullable(userDTO.getBody());
	}
	
	public Optional<List<UserDTO>> getAllUsers(){
		ResponseEntity<List>  users = this.restTemplate
				.getForEntity(USERHOST+"/alluser", 
				List.class);
		return Optional.ofNullable(users.getBody());
		
	}
	
	
	public Optional<UserDTO> getUserDetailsById(String userId){
		ResponseEntity<UserDTO>  users = this.restTemplate
				.getForEntity(USERHOST+"/getUserById/{userId}", 
						UserDTO.class,userId);
		return Optional.ofNullable(users.getBody());
		
	}
	
	
	
	
	
	public Optional<BookDTO> addBookToLibrary(BookDTO book){
		ResponseEntity<BookDTO> bookDTO = this.restTemplate.postForEntity(BOOKSHOST+"/add", book, BookDTO.class);
		return Optional.ofNullable(bookDTO.getBody());
	}
	
	public Optional<BookListDTO> getAllBooks(){
		ResponseEntity<BookListDTO> entity = restTemplate.getForEntity(BOOKSHOST+"/getallbooks",
				BookListDTO.class);	
		System.out.print(entity.getStatusCode());
		return Optional.ofNullable(entity.getBody());
	}
	
	public Optional<List<BookDTO>> getBooksByAuthor( String author){	
		ResponseEntity<List> entity = restTemplate.
				getForEntity(BOOKSHOST+"/getbooksbyauthor/author={author}",
				List.class,
				author);
		System.out.print(entity.getStatusCode());
		return Optional.ofNullable(entity.getBody());
	}
	
	public Optional<List<BookDTO>> getBooksByBookName(String bookname){
			ResponseEntity<List> entity = restTemplate.getForEntity(BOOKSHOST+"getbooksbybookname/bookname={bookname}",List.class,bookname);
			System.out.print(entity.getStatusCode());
			return Optional.ofNullable(entity.getBody());
	}
	
	public Optional<List<BookDTO>> addBooksToDatabase(List<BookDTO> book){
		ResponseEntity<List> entity = restTemplate.postForEntity(BOOKSHOST+"/addbooks",book,List.class);	
		System.out.print(entity.getStatusCode());
		return Optional.ofNullable(entity.getBody());
	}

	@Override
	public Optional assignBookToUser(AssignBookDTO assignBookDTO) {
		ResponseEntity<AssignBookDTO> responseEntity  = this.restTemplate
				.postForEntity(BOOKSHOST+"/book/assign", assignBookDTO, AssignBookDTO.class);
		return Optional.ofNullable(responseEntity.getBody());
	}

	
}
