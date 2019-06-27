package com.haleyysz.discussion.services;

import com.haleyysz.discussion.models.User;
import com.haleyysz.discussion.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	

	public void save(User user){
		userRepository.save(user);
	}
	public List<User> getAllUser(){
		return userRepository.findAll();
	}
	public void deleteAllUser(){
		userRepository.deleteAll();
	}
	public User findUser(String username){
		return userRepository.findByUsername(username);
	}

}
