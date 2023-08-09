package com.example.jwt.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.jwt.entities.AuthUser;

@Repository
public interface AuthUserRepository extends CrudRepository<AuthUser, Integer>{
	
	public AuthUser findByName(String name);
	
	public AuthUser findByEmail(String emailString);
	
	public Boolean existsByName(String name);
	
	public Boolean existsByEmail(String emailString);
}
