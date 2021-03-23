package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController //페이지 이동이 아닌 데이터만 리턴해주는
public class DummyControllerTest {

//	//http://localhost:8000/blog/dummy/join으로 request를 할 건데
//	//이 때 http의 body에 username, password, email 데이터를 가지고 요청하게 되면 3가지 값이 join함수 파라미터에 들어감
//	@PostMapping("/dummy/join") //회원가입할 것이니까. insert하기 위해 postmapping
//	public String join(String username, String password, String email) { //key=value형태의 데이터를 받아줌
//		//user테이블을 보면 id,role,createDate는 자동으로 들어갈 것이므로
//	
//		System.out.println("username" + username);
//		System.out.println("password" + password);
//		System.out.println("email" + email);
//		
//		
//		return "회원가입이 완료되었습니다.";
//	}
	
	
	@Autowired
	private UserRepository userRepository;
	//Spring이 @Restcontrlloer을 읽어서 dummycontrllertest를 메모리에 띄우줄 때 null인데 @Autowired을 붙여주면 
	//메모리에 같이 뜬다. (Autowired는 userRepository타입으로 spring이 관리하는 객체가 있다면 userRepository에 넣어준다)
	//의존성 주입.(DI)
	
	
	@PostMapping("/dummy/join")
	public String join(User user) { //오브젝트로도 받을 수 있음
		
		System.out.println("id : " + user.getId());
		System.out.println("username : " + user.getUsername());
		System.out.println("password: " + user.getPassword());
		System.out.println("email: " + user.getEmail());
		System.out.println("role: " + user.getRole());
		System.out.println("createDate: " + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다.";
	}
}
