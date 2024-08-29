package com.web.bookservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class JoinDto {


    private MultipartFile memberImage;
    @NotBlank
    private String loginId;
    @NotBlank
    private String name;
    @NotBlank
    private String city;
    @NotBlank
    private String password;
}
