package com.bowwow.admin.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.bowwow.common.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	public User findByEmail(String checkEmail);
	
	public User findByNickName(String nickname);
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
	public User getUserbyEmail(@Param("email") String email);

	@Query("SELECT u FROM User u WHERE CONCAT(u.email,' ', u.name, ' ', u.nickName) LIKE %?1%")
	public Page<User> findAll(String keyword , Pageable pageable);

}
