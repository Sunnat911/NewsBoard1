package com.example.news_board_API.repository;

import com.example.news_board_API.entity.RoleEntity;
import com.example.news_board_API.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
    RoleEntity findByRoleEnum(RoleEnum roleEnumList);
}
