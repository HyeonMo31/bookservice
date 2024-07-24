package com.web.bookservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .authorizeHttpRequests((auth) -> auth
                        //누구나 접근
                        .requestMatchers("/", "/login", "/css/**", "/img/**", "/join", "/error").permitAll()
                        //로그인한 사용자 누구나 접근
                        .requestMatchers("/page").authenticated()
                        //역할에 따라 접근 (로그인 필요)
                        .requestMatchers("/admin").hasRole("ADMIN")
                        //역할에 따라 접근 여러가지 (로그인 필요)
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        //위에서 적지 않은 경로를 처리하는 것이 anyRequest이다. anyRequset.authenticated는
                        //위에 적지 않은 경로는 로그인 처리가 되어야한다는 것이다.
                        .anyRequest().authenticated()
                );
        //인가되지 않은 경로에 대해 로그인 페이지로 이동하게 한다.
        http
                .formLogin((auth) -> auth.loginPage("/login")
                        //로그인한 데이터를 특정 경로로 보낸다 그 경로가 loginProc이다.
                        .loginProcessingUrl("/login")
                        .usernameParameter("loginId")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                );

        //토큰을 보내지 않으면 로그인이 되지 않기 떄문에 개발 환경에서만 csrf를 disable할것이다.
        http
                .csrf((auth) -> auth.disable());

        //받은 인자인 http build를 리턴.
        return http.build();
    }
}