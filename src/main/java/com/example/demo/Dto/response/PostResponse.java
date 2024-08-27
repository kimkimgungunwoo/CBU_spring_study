package com.example.demo.Dto.response;


import com.example.demo.domain.Post;
import lombok.*;

@Getter
public class PostResponse {
    private final Long id;

    private final String title;

    private final String content;

    //
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }


    @Builder
    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;

    }

}
