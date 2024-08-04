package com.web.bookservice.service;

import com.web.bookservice.domain.Member;
import com.web.bookservice.domain.Role;
import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.JoinDto;
import com.web.bookservice.dto.MemberResponseDto;
import com.web.bookservice.dto.ResponseCodeDto;
import com.web.bookservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean join(JoinDto joinDTO) {

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

    public MemberResponseDto findLoginMember(CustomMemberDetails member) {

        if(member == null)
            return new MemberResponseDto();

        Member findMember = memberRepository.findByLoginId(member.getUsername());

        return new MemberResponseDto(findMember.getName(), findMember.getLoginId(),
                findMember.getCity(), findMember.getJoinDate());

    }




}
