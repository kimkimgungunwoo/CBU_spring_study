package com.example.demo.repositroy;


import com.example.demo.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface Postrepository extends JpaRepository<Post,Long> {
}
