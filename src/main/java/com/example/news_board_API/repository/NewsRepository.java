package com.example.news_board_API.repository;
import com.example.news_board_API.entity.NewsEntity;
import com.example.news_board_API.enums.NewsStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface NewsRepository extends JpaRepository<NewsEntity,Long> {

   void deleteById(Long newsId);

   List<NewsEntity> getNewsEntitiesByCratedBy(Long userId);

//   @Query(value = "select t.* from news_entity t where t.newsStatusEnum = IN_PROCECC ",nativeQuery = true)
//   Optional<NewsEntity> findNewsEntityByNewsStatusEnum_InProcess();


    List<NewsEntity> findAllByNewsStatusEnum(NewsStatusEnum newsStatusEnum);

}
