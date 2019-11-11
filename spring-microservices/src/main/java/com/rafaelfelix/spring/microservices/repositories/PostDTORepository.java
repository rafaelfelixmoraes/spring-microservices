package com.rafaelfelix.spring.microservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rafaelfelix.spring.microservices.dto.PostsDTO;

@Repository
public interface PostDTORepository extends JpaRepository<PostsDTO, Integer>{

}
