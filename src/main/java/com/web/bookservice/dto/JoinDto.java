package com.web.bookservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JoinDto {

    @NotBlank
    private String loginId;
    @NotBlank
    private String name;
    @NotBlank
    private String city;
    @NotBlank
    private String password;
}
