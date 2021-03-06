package com.pongi.project.springboot.web;


import com.pongi.project.springboot.service.posts.PostsService;
import com.pongi.project.springboot.web.dto.PostsResponseDto;
import com.pongi.project.springboot.web.dto.PostsSaveRequestDto;
import com.pongi.project.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    //REST에서 CRUD는 생성(Create)- POST, 읽기(Read)- GET, 수정(Update)- PUT, 삭제(Delete)-DELETE
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);

        return id;
    }

}
