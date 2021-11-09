package com.example.news_board_API.service;


import com.example.news_board_API.dto.ApiResponse;

public interface BaseService {
    ApiResponse SUCCESS = new ApiResponse("success",true,0);
    ApiResponse USER_EXIST = new ApiResponse("userName already exist",false,-100);
    ApiResponse USER_NOT_FOUND = new ApiResponse("user not found",false,-100);
    ApiResponse NEWS_NOT_FOUND = new ApiResponse("news not found",false,-100);
    ApiResponse DELETE = new ApiResponse("news was delete",true,0);

}
