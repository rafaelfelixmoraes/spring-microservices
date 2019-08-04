package com.rafaelfelix.spring.microservices.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PostsDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Date timestamp;
	private String postContent;
	private UserDTO user;
	
	public PostsDTO(Integer id, Date timestamp, String postContent, UserDTO user) {
		this.id = id;
		this.timestamp = timestamp;
		this.postContent = postContent;
		this.user = user;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
	public Date getTimestamp() {
		return timestamp;
	}

	public String getPostContent() {
		return postContent;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PostsDTO)) {
			return false;
		}
		PostsDTO other = (PostsDTO) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
