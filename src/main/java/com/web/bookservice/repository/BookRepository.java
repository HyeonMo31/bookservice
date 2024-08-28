package com.web.bookservice.repository;

import com.web.bookservice.domain.Book;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

    Book findByIsbn(String isbn);
    boolean existsByIsbn(String isbn);

    /**
     * 리뷰가 가장 많은 책, (개수가 같으면 createdDate 최신순서대로 정렬 후 limit 1개)
     */
    @Query(value = "SELECT r.book FROM Review r " +
            "GROUP BY r.book " +
            "ORDER BY COUNT(r.book) DESC, MAX(r.createdDate) DESC")
    Book findTopBookByReviewCount(Limit limit);

    /**
     * 게시판 언급 가장 많은 책, (개수가 같으면 createdDate 최신순서대로 정렬 후 limit 1개)
     */
    @Query(value = "SELECT p.book FROM Post p " +
            "GROUP BY p.book " +
            "ORDER BY COUNT(p.book) DESC, MAX(p.createdDate) DESC")
    Book findTopBookByPostCount(Limit limit);

    /**
     * 즐겨찾기 가장 많은 책, (개수가 같으면 createdDate 최신순서대로 정렬 후 limit 1개)
     */
    @Query(value = "SELECT b.book FROM Bookmark b " +
            "GROUP BY b.book " +
            "ORDER BY COUNT(b.book) DESC, MAX(b.createdDate) DESC")
    Book findTopBookByBookmarkCount(Limit limit);

}
