package com.pongi.project.springboot.service.posts;

import com.pongi.project.springboot.domain.posts.Posts;
import com.pongi.project.springboot.domain.posts.PostsRepository;
import com.pongi.project.springboot.web.dto.PostsListResponseDto;
import com.pongi.project.springboot.web.dto.PostsResponseDto;
import com.pongi.project.springboot.web.dto.PostsSaveRequestDto;
import com.pongi.project.springboot.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private  final PostsRepository postsRepository;

    @Transactional //선언적 트랜잭션 - 신뢰성 보장
    public Long save(PostsSaveRequestDto requestDto){
        return  postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public  Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당게시글이 없습니다. id="+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        postsRepository.delete(posts);
    }

    @Transactional(readOnly = true) //읽기전용으로 메모리 사용량 최적화, false일 경우 영속성컨텍스트 관리에 따라 스냅샷인스턴스 보관 - 조회속도 개선
    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        //postsRepoitory 결과로 넘어온 Posts의 Stream을 map을 통해 PostsResoponseDto 변환 -> List로 반환
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)//람다식, .map(posts -> new PostsListResponseDto(posts))와 동일
                .collect(Collectors.toList());
    }

}
