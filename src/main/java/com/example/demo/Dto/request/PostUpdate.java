package com.example.demo.Dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdate {
    @NotBlank(message="제목을 입력하세요")
    private String title;

    @NotBlank(message="내용을 입력하세요")
    private String content;

}
