package com.pongi.project.springboot.domain.posts;

//jpa repository는 Mybatis의 Dao 처럼 DB Layer 접근자 이다.
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long>{

    //springDataJpa에서 제공하지 않는 메소드, 또는 가독성을 높이기 위해 직접 쿼리 호출 가능(해당 쿼리는 JPA로도 가능함)
    //대규모 프로젝트에서 데이터 조회는 복잡한 조건을 요구하기때문에 조회용 프레임워크는 따로 사용하기도 함(querydsl,MyBatis,jooq)
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
