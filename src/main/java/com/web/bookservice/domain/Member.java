package com.web.bookservice.domain;

import com.web.bookservice.dto.JoinDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.context.event.EventListener;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @CreatedDate
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Member(String password, String loginId, Role role, String name, String city) {
        this.password = password;
        this.loginId = loginId;
        this.role = role;
        this.name = name;
        this.city = city;
    }

    public void updateMember(JoinDto joinDto) {
        this.name = joinDto.getName();
        this.city =joinDto.getCity();
    }

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
//    private List<Bookmark> bookmarks;
}
