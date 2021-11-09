package com.example.news_board_API.controller;

import com.example.news_board_API.dto.ReceiveNewsDto;
import com.example.news_board_API.entity.UserEntity;
import com.example.news_board_API.config.CurrentUser;
import com.example.news_board_API.dto.ApiResponse;
import com.example.news_board_API.service.NewsService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/news/")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }



    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/add")
    public HttpEntity<?> addNews(@RequestBody ReceiveNewsDto receiveNewsDto, @CurrentUser UserEntity currentUser){
        System.out.println(currentUser.getUsername());
        ApiResponse apiResponse = newsService.addNews(receiveNewsDto,currentUser);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/{id}")
    public HttpEntity<?> getUserNews(
            @PathVariable ("id") Long userId ) {
        ApiResponse apiResponse = newsService.getUserNews(userId);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);

    }
    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/list")
    public ResponseEntity<?> getAllNewsList_ACCEPTED(){
        ApiResponse apiResponse = newsService.getAllNewsList_ACCAPTED();
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PutMapping ("/edit/{id}")
    public HttpEntity<?> editNewsUser(
            @PathVariable ("id") Long newsId,@RequestBody ReceiveNewsDto receiveNewsDto ) {
        ApiResponse apiResponse = newsService.editNewsUser(newsId, receiveNewsDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);


    }

    @PreAuthorize("hasAuthority('USER')")
    @DeleteMapping ("/delete/{id}")
    public HttpEntity<?> deleteNewsUser(
            @PathVariable ("id") Long newsId) {
        ApiResponse apiResponse = newsService.deleteUserNews(newsId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);

    }
}
