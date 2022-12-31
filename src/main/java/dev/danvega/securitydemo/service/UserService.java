package dev.danvega.securitydemo.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import dev.danvega.securitydemo.entity.User;
import dev.danvega.securitydemo.repository.UserRepository;

@Service
@Primary
public class UserService implements ICrudService<User, Long>{

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAll() {
		List<User> lst = userRepository.findAll();
		for (int i = 0; i < lst.size(); i++) {
			lst.get(i).setPassword(null);
		}
		return lst;
	}

	@Override
	public void add(User user) {
		userRepository.save(user);
	}

	@Override
	public void update(User user) {
		userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		User user = new User();
		user.setId(id);
		userRepository.delete(user);
	}

}
