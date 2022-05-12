package com.pongi.project.springboot.web.dto;

import lombok.Getter; //선언된 모든 필드의 get 메소드를 생성
import lombok.RequiredArgsConstructor; //final 필드가 포함된 생성자를 생성

@Getter
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
