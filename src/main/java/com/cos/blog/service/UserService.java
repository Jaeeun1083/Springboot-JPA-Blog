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
}
