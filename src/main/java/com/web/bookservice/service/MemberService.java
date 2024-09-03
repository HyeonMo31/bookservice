package com.web.bookservice.service;

import com.web.bookservice.domain.Member;
import com.web.bookservice.domain.Role;
import com.web.bookservice.domain.UploadFile;
import com.web.bookservice.dto.CustomMemberDetails;
import com.web.bookservice.dto.JoinDto;
import com.web.bookservice.dto.MemberResponseDto;
import com.web.bookservice.dto.ResponseCodeDto;
import com.web.bookservice.exception.ErrorMessage;
import com.web.bookservice.exception.ImageTypeMisException;
import com.web.bookservice.exception.MemberNotAuthenticatedException;
import com.web.bookservice.exception.PasswordBadRequestException;
import com.web.bookservice.repository.FileStore;
import com.web.bookservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.security.sasl.AuthenticationException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.web.bookservice.exception.ErrorMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberDetailsService memberDetailsService;
    private final FileStore fileStore;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public int join(JoinDto joinDto) throws IOException {

         //이미 존재하는지
         boolean isUser = memberRepository.existsByLoginId(joinDto.getLoginId());

         if(isUser)
             return 409;

         //파일 형식이 맞는지
         MultipartFile memberImage = joinDto.getMemberImage();

        if(!memberImage.isEmpty()) {
             List<String> allowedExtensions = List.of("jpg", "png", "gif");
             String extension = fileStore.getExtract(memberImage.getOriginalFilename());
             if(!allowedExtensions.contains(extension.toLowerCase()))
                 return 400;
         }

        //파일을 데이터베이스에는 파일경로만 저장한다.
         Member member = new Member(bCryptPasswordEncoder.encode(joinDto.getPassword()),
                 joinDto.getLoginId(), Role.USER, joinDto.getName(),
                 joinDto.getCity(), fileStore.storeDefaultFile(memberImage));

         memberRepository.save(member);

        return 201;
    }

    public MemberResponseDto findLoginMember(CustomMemberDetails member) {

        if(member == null)
            return new MemberResponseDto();

        Member findMember = memberRepository.findByLoginId(member.getUsername());

        return new MemberResponseDto(findMember);

    }

    public MemberResponseDto updateMember(JoinDto joinDto, CustomMemberDetails member) throws IOException {

        if(member == null)
            throw new MemberNotAuthenticatedException("로그인 되어 있지 않습니다.");

        Member findMember = memberRepository.findByLoginId(member.getUsername());
        MultipartFile memberImage = joinDto.getMemberImage();
        UploadFile uploadFile = null;

        if(!bCryptPasswordEncoder.matches(joinDto.getPassword(), findMember.getPassword())) {
            throw new PasswordBadRequestException();
        }
        //자바스크립트 form.append를 통해 제출했을 때, 값이 없으면 null이 들어온다.
        //반대로 form 태그 제출했을 때, 빈 객체가 들어와 isEmpty에 접근할 수 있다.
        if(memberImage != null) {
            if(!memberImage.isEmpty()) {
                List<String> allowedExtensions = List.of("jpg", "png", "gif");
                String extension = fileStore.getExtract(memberImage.getOriginalFilename());
                if(!allowedExtensions.contains(extension.toLowerCase()))
                    throw new ImageTypeMisException();

                //파일 삭제 // deafult 사진인 tuna이면 삭제하면 안된다.
                if(!findMember.getMemberImage().getStoreFileName().equals("tuna.jpg"))
                    fileStore.deleteFile(findMember.getMemberImage().getStoreFileName());
                //파일저장
                uploadFile = fileStore.storeDefaultFile(joinDto.getMemberImage());
            }
        }

        //스프링 시큐리티의 세션 값을 변경.
        memberDetailsService.updateSecurityContext(findMember.getLoginId());
        
        findMember.updateMember(joinDto, uploadFile);

        return new MemberResponseDto(findMember);
    }




}
