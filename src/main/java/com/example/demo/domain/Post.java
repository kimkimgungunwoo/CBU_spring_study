package com.example.demo.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //접근 못하게 막는 용도->접근 레벨 제한(?)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 생성 전략
    private Long id;
    
    private String title;

    @Lob //문자열을 바이트 데이터로 변경->db부담 없음
    private String content;


    @Builder
    public Post(
            String title,
            String content
    ){
        this.title=title;
        this.content=content;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;

    }

}
