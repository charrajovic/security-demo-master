package dev.danvega.securitydemo.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.danvega.securitydemo.entity.Role;
import dev.danvega.securitydemo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String str);
	
	@Transactional
	@Modifying(flushAutomatically = true)
	@Query("UPDATE User a SET a.username=?1, a.roles=?2 WHERE a.id=?3")
	int setUserActiveStatusFlag(String username,Role roles,Long id);
	
	@Query("SELECT p.password FROM User p WHERE p.id = ?1")
    String findUserPassword(Long id);
}
