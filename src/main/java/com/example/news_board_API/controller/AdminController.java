package com.example.news_board_API.controller;



import com.example.news_board_API.dto.ApiResponse;
import com.example.news_board_API.enums.NewsStatusEnum;
import com.example.news_board_API.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/news/admin")
public class AdminController {
    @Autowired
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/get")
    public HttpEntity<?> getAllNewsInProgress(){
        ApiResponse apiResponse = adminService.getAllNewsInProgress();
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }



    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/changeStatus/{newsId}")
    public HttpEntity<?> changeStatus(@PathVariable Long newsId, @RequestParam NewsStatusEnum status){
        ApiResponse apiResponse=adminService.changeStatus(newsId,status);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);

    }


}
