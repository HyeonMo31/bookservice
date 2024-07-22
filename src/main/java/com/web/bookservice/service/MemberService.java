package com.web.bookservice.service;

import com.web.bookservice.domain.Member;
import com.web.bookservice.domain.Role;
import com.web.bookservice.dto.JoinDTO;
import com.web.bookservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean join(JoinDTO joinDTO) {

         boolean isUser = memberRepository.existsByLoginId(joinDTO.getLoginId());

         if(isUser)
             return false;

         Member member = new Member();
         member.setPassword(bCryptPasswordEncoder.encode(joinDTO.getPassword()));
         member.setJoinDate(LocalDateTime.now());
         member.setLoginId(joinDTO.getLoginId());
         member.setRole(Role.USER);
         member.setName(joinDTO.getName());
         member.setCity(joinDTO.getCity());

         memberRepository.save(member);

        return true;
    }



}
