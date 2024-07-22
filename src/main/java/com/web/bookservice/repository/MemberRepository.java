package com.web.bookservice.repository;

import com.web.bookservice.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByLoginId(String loginId);

    boolean existsByLoginId(String loginId);



}
