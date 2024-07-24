package com.web.bookservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JoinDTO {

    @NotBlank
    private String loginId;
    @NotBlank
    private String name;
    @NotBlank
    private String city;
    @NotBlank
    private String password;
}
