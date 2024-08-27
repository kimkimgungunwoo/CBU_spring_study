package com.example.demo.service;

import com.example.demo.Dto.request.PostCreate;
import com.example.demo.Dto.response.PostResponse;
import com.example.demo.domain.Post;
import com.example.demo.repositroy.Postrepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class PostServiceTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    private PostService postService;

    @Autowired
    private Postrepository postrepository;
    
    @Test
    @DisplayName("글 작성이 잘 되는지")
    void test(){
        //given
        PostCreate request = PostCreate.builder()
                .title("제목입니다")
                .content("내용입니다")
                .build();
        //when
        postService.write(request);
        //then
        assertEquals(1L,postrepository.count()); //알트 엔터 하면 뭔가 됨 import
        Post post = postrepository.findAll().get(0);//ctl alt v 누르면 됨 (build)랑 비슷함
        assertEquals("제목입니다",post.getTitle());
        assertEquals("내용입니다",post.getContent());



    }

    @Test
    @DisplayName("글 단건조회")
    void test2(){
        //given
        //when
        Post post = Post.builder().
                title("제목이에요").
                content("내용이에요")
                .build();
        postrepository.save(post);
        PostResponse response = postService.get(post.getId());

       assertNotNull(response);
       assertEquals(1L,postrepository.count());

       assertEquals("제목이에요",response.getTitle());
       assertEquals("내용이에요",response.getContent());



    }

    @Test
    @DisplayName("글 전체조회")
    void test3(){
        //given
        //when
        List<Post> postList=IntStream.range(0,10)
                        .mapToObj(i->{
                            return Post.builder()
                                    .title("제목입니다"+i)
                                    .content("내용입니다"+i)
                                    .build();
                        }).collect(Collectors.toList());

        postrepository.saveAll(postList);

        List<PostResponse> response = postService.getAll();

        assertEquals(10L,postrepository.count());
        assertEquals("제목입니다9",response.get(9).getTitle());



    }





}