package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController //데이터만 응답해줄 것이므로
public class UserApiController { 
	
	@Autowired //DI할 수 있음. spring이 컴포넌트 스캔할 때 서비스 어노텐션 클래스를 보는 순간 스프링 bean에 등록해서 메모리에 띄워주기 때문에
	private UserService userService; //서비스를 dependency injection
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user){//요청이 json이니까    //user로 받는게 username,password,email 세개 이므로 role은 내가 넣어줘야함
		System.out.println("UserApiController : savee 호출 됨");
		//실제로 DB에 insert를 하고 아래에서 return이 되면 됨.
		user.setRole(RoleType.USER);
		int result = userService.회원가입(user); //받은걸 그대로 넣어서 
		return new ResponseDto<Integer>(HttpStatus.OK, result);
	}
	}
