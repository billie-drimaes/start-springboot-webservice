package com.pongi.project.springboot.web;

//Junit 4 -> 5로 업데이트 되면서 라이브러리 사용법 변경 (주석참고)

//Junit 4: import org.junit.Test;
import com.pongi.project.springboot.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
//Junit 4: import org.junit.runner.RunWith;
import org.junit.jupiter.api.extension.ExtendWith; //junit과 springboot test 간의 연결자 역할
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

//Junit 4: @RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(// web에 집중하는 스프링 어노테이션(controller 사용 시 사용)
        controllers = HelloController.class,
        //WebMvcTest는 WebMvcConfigurer, controller 등을 읽어오지만, 그외에 repository,service,component는 스캔대상이 아니어서, SecurityConfig는 읽어오지만, CustomOAuth2UserService는 읽지못함
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class) //webMvcTest에서 SecurityConfig를 스캔하지 않도록 설정
        })
public class HelloControllerTest {

    @Autowired //Bean 주입
    private MockMvc mvc; //스프링 웹 api test의 시작점

    @Test
    @WithMockUser(roles="USER")
    public void hello가_return된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) //http get 요청
                .andExpect(status().isOk()) //status가 200인지 확인
                .andExpect(content().string(hello)); //return 결과 검증
    }

    @Test
    @WithMockUser(roles="USER")
    public void helloDto_return() throws  Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                        get("/hello/dto")
                                .param("name", name)
                                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));

    }

}
