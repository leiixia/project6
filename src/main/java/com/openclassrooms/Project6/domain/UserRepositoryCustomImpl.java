package com.openclassrooms.Project6.domain;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom  {

	@Autowired
	EntityManager entityManager; 
	
	@Override
	public Long getMaxUserId() {
		try {
			String sql = "SELECT coalesce(max(e.id), 0) FROM User e";
			Query query = entityManager.createQuery(sql);
			return (Long) query.getSingleResult();
		} catch (NoResultException e) {
			return 0L;
		}
	}
	
	@Override
	public long updateUser(Long userId, String userName, String userMail, String userPassword) {
		User e = entityManager.find(User.class, userId);
		if(e == null) {
			return 0;
		}
		e.setName(userName);
		e.setMail(userMail);
		e.setPassword(userPassword);
		return 1; 
	}
	

}
