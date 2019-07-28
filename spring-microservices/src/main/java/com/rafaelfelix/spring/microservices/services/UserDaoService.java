package com.rafaelfelix.spring.microservices.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rafaelfelix.spring.microservices.dto.UserDTO;

@Component
public class UserDaoService {

	private static List<UserDTO> users = new ArrayList<>();
	
	static {
		users.add(new UserDTO(1, "Rafael", new Date()));
		users.add(new UserDTO(2, "José", new Date()));
		users.add(new UserDTO(3, "Abelardo", new Date()));
	}
	
	public List<UserDTO> listAll() {
		return users;
	}
	
	public UserDTO save(UserDTO user) {
		if(user.getId() == null) {
			user.setId(users.size() + 1);
		}
		users.add(user);
		
		return user;	
	}
	
	public UserDTO findOne(int id) {
		for(UserDTO user : users) {
			if(id == user.getId()) {
				return user;
			}
		}
		
		return null;
	}
}
