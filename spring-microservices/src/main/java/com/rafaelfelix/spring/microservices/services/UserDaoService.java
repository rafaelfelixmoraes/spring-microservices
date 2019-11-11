package com.rafaelfelix.spring.microservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rafaelfelix.spring.microservices.dto.UserDTO;
import com.rafaelfelix.spring.microservices.repositories.UserDTORepository;

@Component
public class UserDaoService {

	@Autowired
	private UserDTORepository userRepo;
	
	public List<UserDTO> listAll() {
		return userRepo.findAll();
	}
	
	
	public UserDTO save(UserDTO user) {
		if(user.getId() == null) {
			List<UserDTO> users = listAll();
			user.setId(users.size() + 1);
		}
		
		return userRepo.saveAndFlush(user);	
	}
	
	public Optional<UserDTO> findOne(int id) {
		return userRepo.findById(id);
	}
	
	public void deleteByEntity(UserDTO user) {
		userRepo.delete(user);
	}
}
