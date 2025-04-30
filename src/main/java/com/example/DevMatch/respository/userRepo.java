package com.example.DevMatch.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DevMatch.model.User; 

@Repository
public interface userRepo  extends JpaRepository<User, Integer>{
	
}
