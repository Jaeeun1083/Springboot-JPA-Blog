package com.cos.blog.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Reply {
  @Id //Primary Key
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id; //시퀀스, auto_increment
  
  @Column(nullable = false, length = 200)
  private String content;
  
  @JoinColumn(name="boardId")
  private Board board;
  
}
