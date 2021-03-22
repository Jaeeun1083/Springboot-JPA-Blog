package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청했을 때 HTML파일 응답을 해주는 어노텐션 : @Controller

//사용자가 요청할 때 (Data를)응답을 해주기 위한 어노텐션
@RestController
public class HttpControllerTest {

	private static final String TAG="HttpControllerTest : ";
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG+"getter : "+m.getUsername());
		m.setUsername("coss");
		System.out.println(TAG+"getter"+m.getUsername());
		return "lombok test 완료";
	}
	
	//인터넷 브라우저 요청은 무조건 get요청만 가능하다
	//나머지 요청을 확인하기 위한 postman
	//http://localhost:8080/http/get (select)
	@GetMapping("/http/get")
	public String getTest(@RequestParam Member m) {
		return "get 요청 : " + m.getId() +"," + m.getUsername();
	//get에서 요청 방법은 쿼리스트링밖에 없음.
	}
	
	//http://localhost:8080/http/post (insert)
	@PostMapping("/http/post") //application/json
	public String postTest(@RequestBody Member m) { //MessageConverter(스프링부트)
		return "post 요청 : " + m.getId() +"," + m.getUsername() + "," + m.getPassword() +"," + m.getEmail();
	//post요청은 body에 담아서 보냄
	}
	
	//http://localhost:8080/http/put (update)
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
}


}
