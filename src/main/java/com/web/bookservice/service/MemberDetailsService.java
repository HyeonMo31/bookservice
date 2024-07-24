package com.web.bookservice.service;

import com.web.bookservice.domain.Member;
import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository repository;


    //로그인 검증을 위한 함수.
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

        Member member = repository.findByLoginId(loginId);

        if(member == null) {
            throw null;
        }

        return new CustomMemberDetails(member);
    }
}
