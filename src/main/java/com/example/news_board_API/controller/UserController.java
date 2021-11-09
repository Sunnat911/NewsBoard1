package com.example.news_board_API.controller;

import com.example.news_board_API.dto.ApiResponse;
import com.example.news_board_API.dto.SignInDto;
import com.example.news_board_API.dto.SignUpDto;
import com.example.news_board_API.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news/user")
public class UserController {
    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/get") // test url
    public String blabla(){
        return "Hello";
    }


    @PostMapping("/add")
    public ResponseEntity<?>addUser(@RequestBody SignUpDto signUpDto){
        ApiResponse apiResponse = userService.addUser(signUpDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }


    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody SignInDto signInDto){
        ApiResponse apiResponse = userService.login(signInDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
}
}
