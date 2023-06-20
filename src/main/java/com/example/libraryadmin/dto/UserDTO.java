package com.example.libraryadmin.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
	private String fullname;
	private String password;
	private String email;
	private List<BookDTO> booksAssigned;
}
