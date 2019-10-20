package com.rafaelfelix.spring.microservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafaelfelix.spring.microservices.dto.UserDTO;

@Repository
public interface UserDTORepository extends JpaRepository<UserDTO, Integer>{

}
