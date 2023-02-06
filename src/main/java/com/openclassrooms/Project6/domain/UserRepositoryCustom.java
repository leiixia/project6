package com.openclassrooms.Project6.domain;

public interface UserRepositoryCustom {
	
	public Long getMaxUserId();
	
	public long updateUser(Long userId, String userName, String userMail, String userPassword);

}
