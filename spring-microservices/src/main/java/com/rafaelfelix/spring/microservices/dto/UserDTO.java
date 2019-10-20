package com.rafaelfelix.spring.microservices.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(description = "All details about the User")
@Entity
@EqualsAndHashCode
public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 2, max = 100, message = "Name must be at least 2 and maximun of 100 characters")
	@ApiModelProperty(notes = "Name must be at least 2 and maximun of 100 characters")
	private String name;
	
	@Past(message = "Date Birth must be in the past")
	@ApiModelProperty(notes = "Date Birth must be in the past")
	private Date dateBirth;
	
	@OneToMany(mappedBy = "user")
	private List<PostsDTO> posts;
	
	public UserDTO() {
		
	}

	public UserDTO(Integer id, String name, Date dateBirth) {
		this.id = id;
		this.name = name;
		this.dateBirth = dateBirth;
	}

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
	public Date getDateBirth() {
		return dateBirth;
	}
}
