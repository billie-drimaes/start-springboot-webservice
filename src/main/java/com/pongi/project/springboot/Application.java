package com.pongi.project.springboot;

import org.springframework.boot.SpringApplication;
import  org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing  //WebMvcTest는 entity클래스가 없어 에러 발생 => SpringBootApplication과 분리
// WebMvcTest는 일반적인 configuration을 스캔하지 않기 때문에 JpaConfig파일로 분리
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
