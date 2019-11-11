package com.rafaelfelix.spring.microservices.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rafaelfelix.spring.microservices.dto.PostsDTO;
import com.rafaelfelix.spring.microservices.dto.UserDTO;
import com.rafaelfelix.spring.microservices.resources.exceptions.UserNotFoundException;
import com.rafaelfelix.spring.microservices.services.PostsDaoService;
import com.rafaelfelix.spring.microservices.services.UserDaoService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserDaoService userService;
	
	@Autowired
	private PostsDaoService postsService;

	@GetMapping()
	public ResponseEntity<List<UserDTO>> findAll(){
		List<UserDTO> users = userService.listAll();
		if(users == null || users.isEmpty()) {
			throw new UserNotFoundException("No users found");
		}
		return ResponseEntity.ok(users);
	}
	
	@PostMapping()
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO user){
		UserDTO newUser = userService.save(user);
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(newUser.getId());
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public Resource<UserDTO> findOne(@PathVariable Integer id){
		Optional<UserDTO> user = userService.findOne(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("ID not found - ".concat(id.toString()));
		}
		
		Resource<UserDTO> resource = new Resource<UserDTO>(user.get());
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	
	@GetMapping("/{id}/posts")
	public ResponseEntity<List<PostsDTO>> findAllPostsByUser(@PathVariable Integer id){
		Optional<UserDTO> user = userService.findOne(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("No user found");
		}
		
		List<PostsDTO> userPosts = postsService.listAllPostsByUser(user.get());
		
		if(userPosts == null || userPosts.isEmpty()) {
			throw new UserNotFoundException("No posts found");
		}
		return ResponseEntity.ok(userPosts);
	}
	
	@PostMapping("/{id}/posts")
	public ResponseEntity<?> createPosts(@PathVariable Integer id, @RequestBody PostsDTO userPost){
		Optional<UserDTO> user = userService.findOne(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundException("No user found");
		}
		
		userPost.setUser(user.get());
		PostsDTO newPost = postsService.save(userPost);
		URI uri =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build(newPost.getId());
		return ResponseEntity.created(uri).build();
	}
	
	/*@GetMapping("/{id}/posts/{post_id}")
	public ResponseEntity<PostsDTO> findAllPostsByUser(@PathVariable Integer id, @PathVariable Integer post_id){
		Optional<UserDTO> user = userService.findOne(id);
		PostsDTO userPost = postsService.getPostDetails(user.get(), post_id);
		
		if(userPost == null) {
			throw new UserNotFoundException("No post found for this user");
		}
		return ResponseEntity.ok(userPost);
	}*/
	
	
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable Integer id){
		Optional<UserDTO> user = userService.findOne(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("No post found for this user");
		} else {
			userService.deleteByEntity(user.get());
		}
	}
}
