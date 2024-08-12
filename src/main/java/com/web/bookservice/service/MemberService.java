package com.web.bookservice.service;

import com.web.bookservice.domain.Member;
import com.web.bookservice.domain.Role;
import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.JoinDto;
import com.web.bookservice.dto.MemberResponseDto;
import com.web.bookservice.dto.ResponseCodeDto;
import com.web.bookservice.exception.ErrorMessage;
import com.web.bookservice.exception.MemberNotAuthenticatedException;
import com.web.bookservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

import static com.web.bookservice.exception.ErrorMessage.*;

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

         Member member = new Member(bCryptPasswordEncoder.encode(joinDTO.getPassword()),
                 joinDTO.getLoginId(), Role.USER, joinDTO.getName(), joinDTO.getCity());

         memberRepository.save(member);

        return true;
    }

    public MemberResponseDto findLoginMember(CustomMemberDetails member) {

        if(member == null)
            return new MemberResponseDto();

        Member findMember = memberRepository.findByLoginId(member.getUsername());

        return new MemberResponseDto(findMember.getName(), findMember.getLoginId(),
                findMember.getCity(), findMember.getCreatedDate());

    }




}
