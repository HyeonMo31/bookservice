package com.web.bookservice.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.web.bookservice.domain.UploadFile;
import com.web.bookservice.repository.FileStore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
public class ReviewCommentResponseDto {

    private Long id;
    private String memberImage;
    private String loginId;
    private String name;
    private String text;
    private String createdDate;

    @QueryProjection
    public ReviewCommentResponseDto(Long id, String memberImage, String loginId, String name, String text, LocalDateTime createdDate) {
        this.id = id;
        this.memberImage = memberImage;
        this.loginId = loginId;
        this.name = name;
        this.text = text;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.createdDate = createdDate.format(formatter);
    }


}
