package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service //스프링이 컴포넌트 스캔을 통해서 bean에 등록을 해줌. (IoC를 해준다. = 메모리에 대신 띄워준다)
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional //전체의 서비스를 하나의 트랜잭션으로 묶어줌
	public void 회원가입(User user) {
		userRepository.save(user);
	}
	
	@Transactional(readOnly = true) //Select할 때 트랜잭션이 시작이 되고 서비스가 종료될 때 트랜잭션이 종료가 될건데 이 때까지 정합성을 유지시킬 수 있음 
	public User 로그인(User user) {
		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
}
