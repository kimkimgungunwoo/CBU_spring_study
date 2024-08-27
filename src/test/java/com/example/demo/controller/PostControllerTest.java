package com.example.demo.controller;

import com.example.demo.Dto.request.PostCreate;
import com.example.demo.Dto.response.PostResponse;
import com.example.demo.domain.Post;
import com.example.demo.repositroy.Postrepository;
import com.example.demo.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    PostService postService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    Postrepository  postrepository;
    @Test
    @DisplayName("/post 요청이 잘 되는지")
    void test1() throws Exception{
        PostCreate request = PostCreate.builder()
                .title("제육이다")
                .content("내용이다")
                .build();
        String json=objectMapper.writeValueAsString(request);

        System.out.println(json);

        mockMvc.perform(post("/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
        )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("zz")
    void test3() throws Exception{
        //given
        //when
        Post post=Post.builder().
                title("제목이에요").
                content("내용이에요")
                .build();

        postrepository.save(post);

        mockMvc.perform(get("/post/{postId}",post.getId()).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(post.getId()))
                .andExpect(jsonPath("$.title").value(post.getTitle()))
                .andExpect(jsonPath("$.content").value(post.getContent()))
                .andDo(print());

    }

    @Test
    @DisplayName("게시글없을때에러잘잡냐")
    void test4() throws Exception{
        //given
        //when

        mockMvc.perform(get("/post/{postId}",1L).contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());


    }

    @Test
    @DisplayName("글 전체조회")
    void test5() throws Exception{
        //given
        //when

        List<Post> postList= IntStream.range(0,10)
                .mapToObj(i->{
                    return Post.builder()
                            .title("제목입니다"+i)
                            .content("내용입니다"+i)
                            .build();
                }).collect(Collectors.toList());

        postrepository.saveAll(postList);

        mockMvc.perform(post("/post").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Matchers.is(10)))
                .andExpect(jsonPath("$[9].title").value("제목입니다9"))
                .andDo(print());





    }



}