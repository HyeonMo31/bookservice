package com.web.bookservice.service;

import com.web.bookservice.domain.Member;
import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        return new CustomMemberDetails(member);
    }

    public void updateSecurityContext(String loginId) {
        // 변경된 사용자의 UserDetails를 로드
        UserDetails userDetails = loadUserByUsername(loginId);

        // 새로운 Authentication 객체 생성
        UsernamePasswordAuthenticationToken newAuth =
                new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        // SecurityContextHolder의 Authentication을 새로운 것으로 교체
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
