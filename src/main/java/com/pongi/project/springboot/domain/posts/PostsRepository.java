package com.pongi.project.springboot.domain.posts;

//jpa repository는 Mybatis의 Dao 처럼 DB Layer 접근자 이다.
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long>{
}
