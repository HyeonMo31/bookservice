package com.web.bookservice.service;

import com.web.bookservice.domain.Member;
import com.web.bookservice.domain.Role;
import com.web.bookservice.domain.UploadFile;
import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.JoinDto;
import com.web.bookservice.dto.MemberResponseDto;
import com.web.bookservice.dto.ResponseCodeDto;
import com.web.bookservice.exception.ErrorMessage;
import com.web.bookservice.exception.MemberNotAuthenticatedException;
import com.web.bookservice.exception.PasswordBadRequestException;
import com.web.bookservice.repository.FileStore;
import com.web.bookservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.sasl.AuthenticationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static com.web.bookservice.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final FileStore fileStore;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public boolean join(JoinDto joinDTO) throws IOException {

         boolean isUser = memberRepository.existsByLoginId(joinDTO.getLoginId());

         if(isUser)
             return false;

         //업로드 파일 (원래이름, 서버저장할 이름)으로 바꾼다.
        UploadFile memberImage = fileStore.storeFile(joinDTO.getMemberImage(), joinDTO.getLoginId());

        //파일을 데이터베이스에는 파일경로만 저장한다.
         Member member = new Member(bCryptPasswordEncoder.encode(joinDTO.getPassword()),
                 joinDTO.getLoginId(), Role.USER, joinDTO.getName(), joinDTO.getCity(), memberImage);

         memberRepository.save(member);

        return true;
    }

    public MemberResponseDto findLoginMember(CustomMemberDetails member) {

        if(member == null)
            return new MemberResponseDto();

        Member findMember = memberRepository.findByLoginId(member.getUsername());

        return new MemberResponseDto(findMember);

    }

    public MemberResponseDto updateMember(JoinDto joinDto, CustomMemberDetails member){

        if(member == null)
            throw new MemberNotAuthenticatedException("로그인 되어 있지 않습니다.");

        Member findMember = memberRepository.findByLoginId(member.getUsername());

        if(!bCryptPasswordEncoder.matches(joinDto.getPassword(), findMember.getPassword())) {
            throw new PasswordBadRequestException();
        }

        findMember.updateMember(joinDto);

        return new MemberResponseDto(findMember);
    }




}
