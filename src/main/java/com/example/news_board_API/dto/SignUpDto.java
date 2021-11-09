package com.example.news_board_API.dto;

import com.example.news_board_API.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    private String name;

    private String userName;

    private String password;

    private RoleEnum roleEnum;
}
