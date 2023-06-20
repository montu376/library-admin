package com.example.libraryadmin.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.libraryadmin.dto.AssignBookDTO;

public interface IBookManagement {
	
	public Optional<ResponseEntity<AssignBookDTO>> assignBookToUser(AssignBookDTO assignBookDTO);

}
