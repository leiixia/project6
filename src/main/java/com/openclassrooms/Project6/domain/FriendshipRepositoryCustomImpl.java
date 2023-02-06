package com.openclassrooms.Project6.domain;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class FriendshipRepositoryCustomImpl {
	
	@Autowired
	EntityManager entityManager; 
	
	public Long getMaxFriendshipId() {
		try {
			String sql= "SELECT coalesce(max(e.id), 0) FROM Friendship e";
			Query query = entityManager.createQuery(sql);
			return (Long) query.getSingleResult();
		} catch (NoResultException e) {
			return 0L;
		}
	}
	
	
	public long updateFriendship(Long friendshipId, int firstfriend_id, int secondfriend_id) {
		Friendship e = entityManager.find(Friendship.class, friendshipId);
		if(e== null) {
			return 0;
		}
		e.setFirstfriend_id(firstfriend_id);
		e.setSecondfriend_id(secondfriend_id);
		return 1;
	}

}
