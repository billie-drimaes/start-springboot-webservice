package com.pongi.project.springboot.service.posts;

import com.pongi.project.springboot.domain.posts.PostsRepository;
import com.pongi.project.springboot.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class PostsService {
    private  final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return  postsRepository.save(requestDto.toEntity()).getId();
    }
}
