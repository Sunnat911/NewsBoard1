package com.example.news_board_API.entity;


import com.example.news_board_API.enums.NewsStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class NewsEntity extends BaseEntity {

    private String title;

    private String text;

    private Date createDate;

    private Long cratedBy;

    @Enumerated(EnumType.STRING)
    private NewsStatusEnum newsStatusEnum;

    public NewsStatusEnum getNewsStatusEnum() {
        return newsStatusEnum;
    }

    public void setNewsStatusEnum(NewsStatusEnum newsStatusEnum) {
        this.newsStatusEnum = newsStatusEnum;
    }
}
