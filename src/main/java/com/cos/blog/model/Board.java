package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id;
	
	@Column(nullable = false, length =100)
	private String title;
	
	@Lob //대용량 데이터를 다룰 때 씀
	private String content; //섬머노트 라이브러리를 쓸건데 그러면 <html>태그가 섞여서 디자인 되므로 용량이 매우 커짐
	
	@ColumnDefault("0")
	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER) //Many = Board, One=User. 한명의 유저는 여러개의 게시글을 쓸 수 있다., fetch = FetchType.EAGER : 너가 board테이블을 select하면 user정보는 가져올게. 한 건밖에 없으니까. 
	@JoinColumn(name="userId")
	private User user; //DB는 오브젝트를 저장할 수 없다. 그래서 사용하는 FK. 자바는 오브젝트를 저장할 수 있으므로
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) //하나의 게시글에 여러개의 답글, mappedBy : 연관관계의 주인이 아니다 (난 FK가 아니에요) DB에 컬럼을 만들지 마세요, EAGER전략으로 바꿈 : board정보와 relpy정보를 다 가져와야하니까
   	private List<Reply> reply; //여러개의 답글을 위한 list
	
	@CreationTimestamp
	private Timestamp createDate;
}
