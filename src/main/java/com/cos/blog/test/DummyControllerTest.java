package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@RestController //html이 아니라 data를 리턴해주는 어노텐션.
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
	
	
	
	//삭제하기 테스트
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다. 해당 id는 DB에 없습니다.";
		}	
		
		return "삭제되었습니다. id : "+id;
	}
	
	
	
	//수정하기 테스트
	//email,password를 받아야함 ->수정 하려고
	@Transactional //save를 사용하지 않고 어노테이션을 사용해 더티체킹
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { //json데이터 받으려고 @RequestBody. //json데이터를 요청하면 Java Object(MessageConverter의 Jackson라이브러리가 변환해서 받아줌.
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 실패하였습니다");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());

		//userRepository.save(requestUser);
		return user;
		
		//save함수는 id를 전달하지 않으면 insert를 해주고
		//					 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
		//					 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해준다.
	}
	

	
	//전체 select
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	//페이징 List. 한 페이지 당 2건에 데이터를 리턴받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Direction.DESC) Pageable pageable){
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent(); //내용만 리턴하기 위해.
		return users;
		
	}
	
	// {id} 주소로 파라미터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3  ->3이 아이디로 들어감.
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		//@PathVariable -> 함수 파라미터에서 처리하기 위한 어노텐션
		
		// user의 4번을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 됨
		//그럼 return할 때 null이 리턴이 되니까 프로그램에 문제가 있지 않겠니
		//그러므로 Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return해
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			//Optional이 제공하는 함수 .get() : null이 리턴될 일 없으니 user객체를 뽑겠다
			//											.orElseGet() : null이라면 객체를 생성해서 user에 넣겠다. -> 이 때 들어가는 타입이 supplier타입..
			
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
				// IllegalArgumentException : 잘못된 인수가 들어왔을 경우
			}
		});
		
		// 요청은 웹브라우저
		// user 객체 = 자바 오브젝트. -> 웹브라우저가 이해하지 못함.
		// user 객체가 반환될 때 변환해야함 (웹브라우저가 이해할 수 있는 데이터 -> json)
		// 스프링부트 -> MessageConverter가 응답시에 자동 작동함
		// 만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
		return user;
	}
	
	
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
