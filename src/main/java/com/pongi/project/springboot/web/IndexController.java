package com.pongi.project.springboot.web;

import com.pongi.project.springboot.config.auth.LoginUser;
import com.pongi.project.springboot.config.auth.dto.SessionUser;
import com.pongi.project.springboot.service.posts.PostsService;
import com.pongi.project.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final PostsService postsService;
//    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) { //@LonginUser: (user) httpSession.getAttribute("user") 대체

        //model을 사용해서 서버템플릿엔진(index.mustache)에서 사용할 수 잇는 객체를 저장
        model.addAttribute("posts", postsService.findAllDesc());
//        SessionUser user = (SessionUser) httpSession.getAttribute("user"); //session에 저장된 user정보를 가져옴

        if (user != null) { //session에 저장된 값이 있을 때만 model에 userName으로 등록
            model.addAttribute("userName",user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id); //id로 조회된 결과를 post-update화면으로 전달
        model.addAttribute("post",dto);

        return "posts-update";
    }

}
