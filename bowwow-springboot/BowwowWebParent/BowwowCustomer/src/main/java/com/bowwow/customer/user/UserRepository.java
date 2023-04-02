package com.bowwow.customer.user;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bowwow.common.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {


	public User findByEmail(String email);

	public User findByNickName(String nickName);
	
	

}
