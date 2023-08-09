package com.example.jwt.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.jwt.entities.AuthToken;

public interface AuthTokenRepository extends CrudRepository<AuthToken, Integer>{

}
