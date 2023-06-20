package com.example.libraryadmin.services;

import java.util.Optional;

import com.example.libraryadmin.dto.UserDTO;

public interface IUserManagement {
	
	public Optional<UserDTO> createUser(UserDTO userDTO);

}
