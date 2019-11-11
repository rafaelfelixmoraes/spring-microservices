package com.rafaelfelix.spring.microservices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rafaelfelix.spring.microservices.dto.PostsDTO;
import com.rafaelfelix.spring.microservices.dto.UserDTO;
import com.rafaelfelix.spring.microservices.repositories.PostDTORepository;

@Component
public class PostsDaoService {
	
	@Autowired
	private PostDTORepository postRepo;
	
	public List<PostsDTO> listAllPostsByUser(UserDTO user) {
		List<PostsDTO> userPosts = user.getPosts();
		
		return userPosts;
	}
	
	public PostsDTO save(PostsDTO post) {
		if(post.getId() == null) {
			List<PostsDTO> posts = listAllPostsByUser(post.getUser());
			post.setId(posts.size() + 1);
		}
		
		return postRepo.saveAndFlush(post);	
	}
	
	/*public PostsDTO findOne(int id) {
		populateUsers();
		for(PostsDTO post : posts) {
			if(id == post.getId()) {
				return post;
			}
		}
		
		return null;
	}
	
	public PostsDTO getPostDetails(UserDTO user, Integer postId) {
		populateUsers();
		List<PostsDTO> userPosts = this.listAllPostsByUser(user);
		
		for(PostsDTO post : userPosts) {
			if(post.getId() == postId) {
				return post;
			}
		}
		return null;
	}*/

}
