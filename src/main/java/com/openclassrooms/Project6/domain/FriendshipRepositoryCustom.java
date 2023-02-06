package com.openclassrooms.Project6.domain;

public interface FriendshipRepositoryCustom {

	public Long getMaxFriendshipId();
	
	public long updateFriendship(Long friendshipId, int firstfriend_id, int secondfriend_id);
}
