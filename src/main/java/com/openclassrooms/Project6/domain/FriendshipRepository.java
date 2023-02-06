package com.openclassrooms.Project6.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface FriendshipRepository extends CrudRepository<Friendship, Long>{
	
	List<Friendship> findByFirstfriendId(int firstfriend_id);
	List<Friendship> findBySecondfriendId(Long secondfriend_id);
	
	

}
