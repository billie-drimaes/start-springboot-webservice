package com.pongi.project.springboot.config.auth;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //annotation이 생성될 수 있는 위치를 지정 - Parameter로 선언된 객체에서만 사용가능
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { //LoginUser 어노테이션 생성

}
