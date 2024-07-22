package com.web.bookservice.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@ToString
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;

    private String city;

    private LocalDateTime joinDate;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<Bookmark> bookmarks;
}
