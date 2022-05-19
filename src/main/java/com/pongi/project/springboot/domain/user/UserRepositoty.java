package com.pongi.project.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoty extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email); //이미 생성된 사용자인지 신규가입인지 판단하기위해 사용
}