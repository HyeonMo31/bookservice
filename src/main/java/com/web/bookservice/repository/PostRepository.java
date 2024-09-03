package com.web.bookservice.repository;

import com.web.bookservice.domain.Post;
import com.web.bookservice.dto.PostLatestDto;
import com.web.bookservice.repository.querydsl.PostRepositoryCustom;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

    @Query("select new com.web.bookservice.dto.PostLatestDto(p.id, p.title, m.name, p.createdDate, b.image) " +
            "from Post p join p.book b join p.member m order by p.createdDate desc")
    List<PostLatestDto> findTop3ByCreatedDate(Limit limit);

}
