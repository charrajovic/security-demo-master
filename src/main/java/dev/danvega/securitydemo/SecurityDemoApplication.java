package dev.danvega.securitydemo;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import dev.danvega.securitydemo.entity.Produit;
import dev.danvega.securitydemo.entity.Role;
import dev.danvega.securitydemo.entity.User;
import dev.danvega.securitydemo.repository.ProduitRepository;
import dev.danvega.securitydemo.repository.RoleRepository;
import dev.danvega.securitydemo.repository.UserRepository;
import dev.danvega.securitydemo.util.RoleEnum;

@SpringBootApplication
public class SecurityDemoApplication {

    public static void main(String[] args) {
    	ConfigurableApplicationContext ctx = SpringApplication.run(SecurityDemoApplication.class, args);
		
		ProduitRepository produitRepository = ctx.getBean(ProduitRepository.class);
		
		produitRepository.save(new Produit("Livre", 50, 20));
		produitRepository.save(new Produit("Cahier", 200, 5.25f));
		produitRepository.save(new Produit("Stylo", 500, 2.10f));
		
		RoleRepository roleRepository = ctx.getBean(RoleRepository.class);
		
		Role roleUser = new Role(RoleEnum.ROLE_USER);
		Role roleAdmin = new Role(RoleEnum.ROLE_ADMIN);
		
		roleRepository.save(roleUser);
		roleRepository.save(roleAdmin);
		
		UserRepository userRepository = ctx.getBean(UserRepository.class);
		
		User user = new User("user", "password1", true);
		user.setRoles(Arrays.asList(roleUser));
		
		userRepository.save(user);
		
		User admin = new User("admin", "password2", true);
		admin.setRoles(Arrays.asList(roleUser, roleAdmin));
		
		userRepository.save(admin);
    }

}
