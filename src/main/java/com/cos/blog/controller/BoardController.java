package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	//아무것도 안적었을 때랑 /붙였을 때 이동하겠다
	@GetMapping({"","/"})
	public String index() {
		//prefix로 /WEB-INF, suffix로 .jsp가 붙어서 실행됨
		return "index";

	}
}