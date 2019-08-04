package com.rafaelfelix.spring.microservices.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rafaelfelix.spring.microservices.dto.PostsDTO;
import com.rafaelfelix.spring.microservices.dto.UserDTO;

@Component
public class PostsDaoService {
	
	private static List<UserDTO> users = UserDaoService.users;
	private static List<PostsDTO> posts = new ArrayList<>();
	private static Integer id = 0;
	
	static {
		users.forEach(user -> {
			posts.add(new PostsDTO(++id, new Date(), "Lorem Ipsum LALALALALALALA Birrrlll!!", user));
		});
	}
	
	public List<PostsDTO> listAllPostsByUser(UserDTO user) {
		List<PostsDTO> userPosts = new ArrayList<>();
		posts.forEach(post -> {
			if(user == post.getUser()) {
				userPosts.add(post);
			}
		});
		
		return userPosts;
	}
	
	public PostsDTO save(PostsDTO post) {
		if(post.getId() == null) {
			post.setId(posts.size() + 1);
		}
		posts.add(post);
		
		return post;	
	}
	
	public PostsDTO findOne(int id) {
		for(PostsDTO post : posts) {
			if(id == post.getId()) {
				return post;
			}
		}
		
		return null;
	}
	
	public PostsDTO getPostDetails(UserDTO user, Integer postId) {
		List<PostsDTO> userPosts = this.listAllPostsByUser(user);
		
		for(PostsDTO post : userPosts) {
			if(post.getId() == postId) {
				return post;
			}
		}
		return null;
	}

}
