package com.blog.blogapp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

    public static void main(String[] args) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println("Kshitij(ADMIN)->"+passwordEncoder.encode("123"));
        System.out.println("Scout(LEADER)->"+passwordEncoder.encode("123UWU"));
        System.out.println("Retros(USER)->"+passwordEncoder.encode("legend"));
    }
}
