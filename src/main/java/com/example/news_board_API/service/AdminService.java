package com.example.news_board_API.service;


import com.example.news_board_API.dto.ApiResponse;
import com.example.news_board_API.entity.NewsEntity;
import com.example.news_board_API.enums.NewsStatusEnum;
import com.example.news_board_API.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements BaseService {
    @Autowired
    private final NewsRepository newsRepository;

    public AdminService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }


    public ApiResponse getAllNewsInProgress() { // see only newsList InProcess  for admin
        List<NewsEntity> newsEntityList = newsRepository.findAllByNewsStatusEnum(NewsStatusEnum.IN_PROCESS);
        if(newsEntityList.isEmpty())
            return NEWS_NOT_FOUND;

        SUCCESS.setData(newsEntityList);
        return SUCCESS;


    }


        public ApiResponse changeStatus(Long newsId, NewsStatusEnum status) {
            Optional<NewsEntity> byId = newsRepository.findById(newsId);
            if(byId.isEmpty())
                return NEWS_NOT_FOUND;

            NewsEntity newsEntity = byId.get();
            newsEntity.setNewsStatusEnum(status);
            newsRepository.save(newsEntity);
            return new ApiResponse("a news status have changed to" + " " + status, true );
        }
}
