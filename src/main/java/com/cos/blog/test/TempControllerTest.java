package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//파일을 리턴하기 위해서.
@Controller
public class TempControllerTest {

	//http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("blog home");
		//파일 리턴 기본경로 : src/main/resources/static
		// ->static은 정적 파일만 인식할 수 있음. 
		//리턴명 : /가 앞에 붙어야함.
		//풀 경로 : src/main/resources/static/home.html
		//경로를 바꿔주기 위해서는 application.yml에서 설정을 해야함
		return "/home.html";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		//prefix : /WEB-INF/views/
		//suffix : jsp 이므로
		//풀네임 : /WEB-INF/views/test.jsp
		return"test";
	}
	
}
