package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것.

@Configuration //빈 등록 (IoC관리)
@EnableWebSecurity //시큐리티 필터를 등록하겠다
@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다. 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean //IoC가 되게함. <- 함수가 리턴하는 값을 스프링이 관리.
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
		//이 객체를 통해서 비밀번호를 해쉬화 함.
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //csrf 토큰 비활성화
			.authorizeRequests() //request가 들어오면
				.antMatchers("/","/auth/**", "/js/**", "/css/**", "/image/**") //누구나 들어올 수 있는 주소
				.permitAll()
				.anyRequest() //이게 아닌 다른 모든 요청은
				.authenticated() //인증이 되어야한다.
			.and()
				.formLogin()
				.loginPage("/auth/loginForm");
	}
}
