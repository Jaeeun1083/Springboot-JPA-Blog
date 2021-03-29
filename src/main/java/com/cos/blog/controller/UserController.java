package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

		//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth만 허용하기위해. /auth/**
		//주소가 그냥 /인 index.jsp 허용
		//static 이하에 있는 resorce파일들 /js/**, /css/**, /image/** 허용
		@GetMapping("/auth/joinForm") 
		public String joinForm() {			
			return "user/joinForm";
	}
		
		@GetMapping("/auth/loginForm")
		public String loginForm() {			
			return "user/loginForm";
	}
	
}
