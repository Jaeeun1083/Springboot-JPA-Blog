package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

//자동으로 bean등록이 되므로 @Repository가 자동으로 등록된다. (생략 가능하다)
public interface UserRepository extends JpaRepository<User, Integer>{
//해당 JpaRepository에는 user테이블이 관리하는 repository이고 user테이블의 pk는 integer이다.

	

}
