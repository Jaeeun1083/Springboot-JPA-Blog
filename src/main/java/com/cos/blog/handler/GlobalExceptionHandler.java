package com.cos.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;

@ControllerAdvice//어디서든 exception이 발생하면 오게하기 위해
@RestController
public class GlobalExceptionHandler {

	//Exception이 발생했을 때 실행시킬 함수.	
	@ExceptionHandler(value=Exception.class) //exception이 발생하면 이 함수에 전달해주고
	public ResponseDto<String> handelArgumentException(Exception e) {
		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		//return "<h1>" + e.getMessage() + "</h1>";
		//e.getMessage()리턴이 됨
	}	

	
}
