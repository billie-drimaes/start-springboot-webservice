package com.pongi.project.springboot.config.auth;

import com.pongi.project.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //spirng Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console사용을 위해 crsf,headers().frameOptions 해제
                .and()
                    .authorizeRequests()//URL별 권한 관리를 설정하는 옵션의 시작(필수 선언)
                    .antMatchers("/","/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() //전체 열람 권한
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())//USER권한만 열람 가능
                    .anyRequest().authenticated()//설정값 이외 나머지 URL들은 모두 인증된 사용자 (로그인한 사용자)에게만 허용
                .and()
                    .logout()//로그아웃 기능에 대한 설정 진입
                        .logoutSuccessUrl("/")//로그아웃 성공 시 "/"주소로 이동
                .and()
                    .oauth2Login()//로그인 기능에 대한 설정 진입
                        .userInfoEndpoint()//OAuth2 로그인 성공 이후 사용자 정보를 가져올때 설정을 담당
                            .userService(customOAuth2UserService); //소셜로그인 성공 시 후속조치를 진행할 UserService의 구현체를 등록
    }
}
