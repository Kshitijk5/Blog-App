package com.blog.blogapp.service;

import com.blog.blogapp.Dto.LoginDto;
import com.blog.blogapp.Dto.RegisterDto;

public interface AuthService {

   String login(LoginDto loginDto);
   String register(RegisterDto registerDto);
}
