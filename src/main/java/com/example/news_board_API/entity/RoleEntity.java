package com.example.news_board_API.entity;

import com.example.news_board_API.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)

public class RoleEntity extends BaseEntity implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;





    @Override
    public String getAuthority() {
        return roleEnum.name();
    }

}

