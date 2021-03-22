package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

//테이플화 하기 위한 어노텐션. user클래스가 MySQL에 테이블이 생성이 된다.
@Entity
public class User {
	
	@Id //Pk설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) //IDENTITY : 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int id; //오라클에서 시퀀스. mysql에서는 auto_increment로 넘버링하는 전략을 가져갈 것임
	
	@Column(nullable =false, length =30) //null값이 될 수 없도록, 길이는 30자 이상이 될 수 없도록 설정
	private String username; //아이디
	
	@Column(nullable =false, length =100) //넉넉하게 길이를 주는 이유 : hash로 암호화하기위해
	private String password;
	
	@Column(nullable =false, length =50)
	private String email;
	
	@ColumnDefault("'user'")
	private String role; //Enum을 쓰는게 좋다. -> 데이터의 도메인을 만들어주기 때문에.
	//role은 admin이나 user등 역할을 설정하여 권한을 부여하기 위해 설정하는데
	//String타입으로 두면 오타가 나도 
	
	@CreationTimestamp //회원가입한 시간이 자동으로 입력이 된다.
	private Timestamp createDate;

}
