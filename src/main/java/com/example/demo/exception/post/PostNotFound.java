package com.example.demo.exception.post;

import com.example.demo.exception.GlobalException;

public class PostNotFound extends GlobalException {
    public static final String MESSAGE = "Post not found";
    public PostNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
