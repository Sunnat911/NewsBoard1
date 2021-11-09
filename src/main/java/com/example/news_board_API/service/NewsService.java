package com.example.news_board_API.service;

import com.example.news_board_API.dto.ApiResponse;
import com.example.news_board_API.dto.ReceiveNewsDto;
import com.example.news_board_API.entity.NewsEntity;
import com.example.news_board_API.entity.UserEntity;
import com.example.news_board_API.enums.NewsStatusEnum;
import com.example.news_board_API.repository.NewsRepository;
import com.example.news_board_API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService implements BaseService {

    @Autowired
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public NewsService(NewsRepository newsRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    public ApiResponse addNews(ReceiveNewsDto receiveNewsDto, UserEntity currentUser){

        Optional<UserEntity> userName = userRepository.findByUserName(currentUser.getUsername());

        if(userName.isPresent()){
            NewsEntity newsEntity = new NewsEntity();

            newsEntity.setTitle(receiveNewsDto.getTitle());
            newsEntity.setText(receiveNewsDto.getText());
            newsEntity.setId(currentUser.getId());
            newsEntity.setCratedBy(currentUser.getId());
            newsEntity.setCreateDate(new Date(System.currentTimeMillis()));
            newsEntity.setNewsStatusEnum(NewsStatusEnum.IN_PROCESS);

            newsRepository.save(newsEntity);
            SUCCESS.setData("you have added a new post" + " " +new Date(System.currentTimeMillis()));
        }
        return SUCCESS;
    }

        public ApiResponse getAllNewsList_ACCAPTED(){ //    to see newsList only ACCEPTED for simple User
            List<NewsEntity> newsEntityList = newsRepository.findAllByNewsStatusEnum(NewsStatusEnum.ACCEPTED);
            if(newsEntityList.isEmpty())
                return NEWS_NOT_FOUND;

            SUCCESS.setData(newsEntityList);
            return SUCCESS;
    }

        public ApiResponse getUserNews(long userId){ // see only personal user news

            List<NewsEntity> newsEntityByCratedBy = newsRepository.getNewsEntitiesByCratedBy(userId);
            if(newsEntityByCratedBy.isEmpty())
                return NEWS_NOT_FOUND;

            SUCCESS.setData(newsEntityByCratedBy);

            return SUCCESS;
    }



    public ApiResponse editNewsUser(Long newsId,ReceiveNewsDto receiveNewsDto) {// this method for simple uer
        Optional<NewsEntity> newsEntity = newsRepository.findById(newsId);
        if(newsEntity.isEmpty())
            return NEWS_NOT_FOUND;

        Optional<NewsEntity> newsDb = newsRepository.findById(newsId);
        NewsEntity newsEntity1 = newsDb.get();
        newsEntity1.setTitle(receiveNewsDto.getTitle());
        newsEntity1.setText(receiveNewsDto.getText());
        newsRepository.save(newsEntity1);

        Optional<NewsEntity> editNews = newsRepository.findById(newsId);
        NewsEntity newsEntity2 = editNews.get();
        SUCCESS.setData(newsEntity2);

        return SUCCESS;

    }

    public ApiResponse deleteUserNews(Long newsId){
        Optional<NewsEntity> byId = newsRepository.findById(newsId);
        if(byId.isEmpty())
            return NEWS_NOT_FOUND;

        newsRepository.deleteById(newsId);

        return DELETE;
    }

}
