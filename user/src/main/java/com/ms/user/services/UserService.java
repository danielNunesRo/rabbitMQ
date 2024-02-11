package com.ms.user.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ms.user.models.UserModel;
import com.ms.user.producers.UserProduce;
import com.ms.user.repositories.UserRepository;

@Service
public class UserService {

	
	private final UserRepository userRepository;
	
	final UserProduce userProducer;

	
	public UserService(UserRepository userRepository, UserProduce userProduce) {
		this.userRepository = userRepository;
		this.userProducer = userProduce;
	}


	@Transactional
	public UserModel save(UserModel userModel) {
		userModel = userRepository.save(userModel);
		userProducer.publishMessageEmail(userModel);
		return userModel;
	}
	
	
	
	
	
}
