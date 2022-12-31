package dev.danvega.securitydemo.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.danvega.securitydemo.entity.Role;
import dev.danvega.securitydemo.entity.User;
import dev.danvega.securitydemo.repository.RoleRepository;
import dev.danvega.securitydemo.repository.UserRepository;
import dev.danvega.securitydemo.service.ICrudService;
import dev.danvega.securitydemo.util.RoleEnum;

@RestController
@RequestMapping(path="/crud_user")
public class CrudUserController {

	@Autowired
	private ICrudService<User, Long> userService;
	
	@Autowired
	private RoleRepository roleRepository;

	@GetMapping
	public List<User> getAll(){
		return userService.getAll();
	}

	@PostMapping
	public void add(@RequestBody User user) {
		
		Role rl = roleRepository.findAllRolesByName("ROLE_USER");
		user.setRoles(Arrays.asList(rl));
		System.out.println(user.getRoles());
		userService.add(user);
	}

	@PutMapping
	public void update(@RequestBody User user) {
		userService.update(user);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}
}
