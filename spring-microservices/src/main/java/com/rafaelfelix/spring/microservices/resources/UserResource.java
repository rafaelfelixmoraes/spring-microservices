package com.rafaelfelix.spring.microservices.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafaelfelix.spring.microservices.dto.UserDTO;
import com.rafaelfelix.spring.microservices.services.UserDaoService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserDaoService userService;

	@GetMapping()
	public ResponseEntity<List<UserDTO>> findAll(){
		List<UserDTO> users = userService.listAll();
		return ResponseEntity.ok(users);
	}
	
	@PostMapping()
	public ResponseEntity<?> save(@RequestBody UserDTO user){
		UserDTO newUser = userService.save(user);
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(newUser.getId());
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> findOne(@PathVariable Integer id){
		UserDTO user = userService.findOne(id);
		return ResponseEntity.ok(user);
	}
}
