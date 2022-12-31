package dev.danvega.securitydemo.repository;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.danvega.securitydemo.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{

	@Query("SELECT p FROM Role p WHERE p.name = ?1")
    Role findAllRolesByName(String RoleName);
	
}
