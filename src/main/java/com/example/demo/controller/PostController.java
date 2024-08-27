package com.example.demo.controller;


import com.example.demo.Dto.request.PostCreate;
import com.example.demo.Dto.response.PostResponse;
import com.example.demo.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    @PostMapping("/post")
    public void post(@RequestBody @Valid PostCreate request) {
        postService.write(request);

    }
    @GetMapping("/post/{postId}")
    public PostResponse get(@PathVariable("postId")  @Valid Long id){
        return postService.get(id);
    }

    @GetMapping("/post")
    public List<PostResponse> getAll(){
        return postService.getAll();
    }
}


