package com.rafaelfelix.spring.microservices.dto;

import java.io.Serializable;
import java.util.Date;

import javax.crypto.spec.PSource;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "All details about Posts of a User")
@Entity
@EqualsAndHashCode
public class PostsDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Date timestamp;
	private String postContent;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private UserDTO user;
	
	public PostsDTO () {
		
	}
	
	public PostsDTO(Integer id, Date timestamp, String postContent, UserDTO user) {
		this.id = id;
		this.timestamp = timestamp;
		this.postContent = postContent;
		this.user = user;
	}

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
	public Date getTimestamp() {
		return timestamp;
	}
	
}