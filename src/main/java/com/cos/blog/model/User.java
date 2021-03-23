package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//테이플화 하기 위한 어노텐션. user클래스가 MySQL에 테이블이 생성이 된다.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder //빌더 패턴
//ORM -> Java (다른언어) Object -> 테이블로 매핑해주는 기술
@Entity //User클래스가 MySQL에 테이블이 생성 된다.
//@DynamicInsert //insert할 때 null인 필드를 제외시켜줌
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
	
	//@ColumnDefault("USER")
	//DB는 RoleType이 없다. 그래서 Enum타입을 알려줘야함
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는게 좋다. -> 데이터의 도메인을 만들어주기 때문에. (범위 안의 데이터를 넣을 때)
	//role은 ADMIN이나 USER등 역할을 설정하여 권한을 부여하기 위해 설정하는데
	//String타입으로 두면 오타가 나도 알 수 없는데 RoleType을 사용하면 
	
	
	@CreationTimestamp //회원가입한 시간이 자동으로 입력이 된다.
	private Timestamp createDate;

}