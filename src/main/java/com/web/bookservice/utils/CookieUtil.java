package com.web.bookservice.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Component
public class CookieUtil {

    public String getCookieValue(HttpServletRequest request, HttpServletResponse response, String key) {

        Cookie[] cookies = request.getCookies();

        if(cookies != null) {
            Optional<Cookie> findCookie = Arrays.stream(cookies)
                    .filter(cookie -> cookie.getName().equals(key))
                    .findFirst();
            if(findCookie.isPresent())
                return findCookie.get().getValue();
        }
        return createCookie(response, key);
    }

    private String createCookie(HttpServletResponse response, String key) {

        String uuid = UUID.randomUUID().toString();
        Cookie cookie = new Cookie(key, uuid);
        cookie.setPath("/");
        cookie.setMaxAge(60);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);

        response.addCookie(cookie);

        return uuid;
    }

}
