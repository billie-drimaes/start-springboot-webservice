package com.pongi.project.springboot.config.auth.dto;

import com.pongi.project.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

//세션에 사용자 정보를 저장하기 위한 dto클래스
/*세션에 저장하기 위해서는 클래스의 직렬화가 필요한데,
USER 엔터티클래스에 직렬화를 하게되면 엔터티 클래스의 다른 관계형성에 따라 성능이슈가 발생하기 때문에,
직렬화 기능을 가진 세션dto를 하나 더 만드는 것을 추천*/
@Getter
public class SessionUser implements Serializable { //클래스 직렬화
//인증된 사용자의 정보만 필요
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
