package com.pongi.project.springboot.config.auth.dto;


import com.pongi.project.springboot.domain.user.Role;
import com.pongi.project.springboot.domain.user.User;
import com.sun.xml.internal.ws.api.ha.StickyFeature;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
//OAuthAttributes: OAuth2UserService를 통해 가져온 OAuth2User의 attributes를 담을 클래스
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    //of(google,naver...): OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를 변환해야함
    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        /* if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        } */
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public User toEntity() { //User엔터티 생성, OAuthAttruibutes에서 엔티티를 생성하는 시점은 처음 가입할 때
        return User.builder()
                .name(name)
                .email(email)
                .picture(picture)
                .role(Role.GUEST) //가입 시 기본권한을 guest로 줌
                .build();
    }
}
