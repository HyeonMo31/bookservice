package com.web.bookservice.repository;

import com.web.bookservice.domain.Post;
import com.web.bookservice.repository.querydsl.PostRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {


}
