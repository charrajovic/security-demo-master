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
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public List<User> getAll(){
		return userService.getAll();
	}

	@PostMapping
	public void add(@RequestBody User user) {
		System.out.println(user.getRoles().get(0).getName());
		User newUser = new User(user.getUsername(), user.getPassword(), user.isEnable());
		if(user.getRoles().get(0).getName().equals("ROLE_ADMIN"))
		{
			Role rl = roleRepository.findAllRolesByName(user.getRoles().get(0).getName());
			Role r2 = roleRepository.findAllRolesByName("ROLE_USER");
			newUser.setRoles(Arrays.asList(rl,r2));
		}
		else
		{
			Role rl = roleRepository.findAllRolesByName(user.getRoles().get(0).getName());
			newUser.setRoles(Arrays.asList(rl));
		}
		
		System.out.println(newUser.getRoles());
		
		userService.add(newUser);
	}

	@PutMapping
	public void update(@RequestBody User user) {
		System.out.println(user.isEnable());
		System.out.println(user.getRoles().get(0).getName());
		if(user.getRoles().get(0).getName().equals("ROLE_USER"))
		{
			Role rl = roleRepository.findAllRolesByName(user.getRoles().get(0).getName());
			user.setRoles(Arrays.asList(rl));
		}
		else
		{
			Role rl = roleRepository.findAllRolesByName(user.getRoles().get(0).getName());
			Role r2 = roleRepository.findAllRolesByName("ROLE_USER");
			user.setRoles(Arrays.asList(rl,r2));
		}
		System.out.println(user.getPassword());
		if(user.getPassword() != null)
		{
			
			System.out.println(user.getPassword());
		}
		else
		{
			System.out.println("mablanch");
			user.setPassword(userRepository.findUserPassword(user.getId()));
		}
		
		userService.update(user);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}
}
