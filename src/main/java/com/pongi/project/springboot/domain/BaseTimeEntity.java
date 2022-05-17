package com.pongi.project.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA entity 클래스들이 baseTimeEntity를 상속할 경우 필드들(createdDate,modifiedDate)도 column으로 인식
@EntityListeners(AuditingEntityListener.class) //BaseTimeEntity 클래스에 *Auditing기능 포함 - *JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능
public class BaseTimeEntity {

    @CreatedDate //Entity가 생성되어 저장될 때 시간이 자동 저장됨
    private LocalDateTime createdDate;

    @LastModifiedDate //조회된 entity값을 변경 할 때 시간이 자동 저장됨
    private LocalDateTime modifiedDate;
}
