package com.openclassrooms.Project6.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>{
	
	List<User> findByName(String name);
	List<User> findByMail(String mail);
	

}
