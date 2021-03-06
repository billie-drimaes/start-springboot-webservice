package com.pongi.project.springboot.domain.posts;


import com.pongi.project.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;


@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity { //BaseTimeEntity 상속 안하면 작동 안함

    @Id //PK필드값
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK 생성규칙 - auto_increment
    private Long id; //MySQL 기준 bigint 타입, 가능하면 pk는 long id + auto increment 추천

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private  String author;

    @Builder //빌더 패턴 클래스 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
