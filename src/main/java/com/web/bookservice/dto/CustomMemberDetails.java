package com.web.bookservice.dto;

import com.web.bookservice.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class CustomMemberDetails implements UserDetails {

    private Member member;

    public CustomMemberDetails(Member member) {
        this.member = member;
    }

    //권한에 대한 정보를 리턴한다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();

        collection.add(new GrantedAuthority() {

            @Override
            public String getAuthority() {
                return member.getRole().name();
            }
        });

        return collection;
    }

    public Member getMember() {
        return this.member;
    }

    //db 구현을 하지 않아서 기본적으로 true
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    //db 구현을 하지 않아서 기본적으로 true
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //db 구현을 하지 않아서 기본적으로 true
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    //db 구현을 하지 않아서 기본적으로 true
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getLoginId();
    }
}
