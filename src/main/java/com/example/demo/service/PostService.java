package com.example.demo.service;

import com.example.demo.Dto.request.PostCreate;
import com.example.demo.Dto.response.PostResponse;
import com.example.demo.domain.Post;
import com.example.demo.exception.post.PostNotFound;
import com.example.demo.repositroy.Postrepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional //db데이터 변할때 체크해줌
public class PostService {

    private final Postrepository postrepository;

    public void write(PostCreate request) {
        Post post = Post.builder()
                .content(request.getContent())
                .title(request.getTitle())
                .build();
        postrepository.save(post);
    }


    public PostResponse get(Long id) {
        Post post = postrepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("존재하지 않는 글 입니다"));//optinal->null허용
                .orElseThrow(PostNotFound::new);

        return PostResponse.builder().
                id(post.getId()).
                title(post.getTitle()).
                content(post.getContent()).build();

    }

    public List<PostResponse> getAll() {
        List<Post> postList = postrepository.findAll();
        return postList.stream().map(PostResponse::new).collect(Collectors.toList());

    }
}
