package com.rafaelfelix.spring.microservices.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@Size(min = 2, max = 100, message = "Name must be at least 2 and maximun of 100 characters")
	private String name;
	
	@Past(message = "Date Birth must be in the past")
	private Date dateBirth;
	
	public UserDTO() {
		
	}

	public UserDTO(Integer id, String name, Date dateBirth) {
		this.id = id;
		this.name = name;
		this.dateBirth = dateBirth;
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
		if (!(obj instanceof UserDTO)) {
			return false;
		}
		UserDTO other = (UserDTO) obj;
		return Objects.equals(id, other.id);
	}

	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
	public Date getDateBirth() {
		return dateBirth;
	}
}
