package com.example.news_board_API.service;

import com.example.news_board_API.dto.ApiResponse;
import com.example.news_board_API.dto.SignInDto;
import com.example.news_board_API.dto.SignUpDto;
import com.example.news_board_API.entity.UserEntity;
import com.example.news_board_API.enums.RoleEnum;
import com.example.news_board_API.repository.RoleRepository;
import com.example.news_board_API.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements BaseService {

    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @Value("${jwt.expiry.date}")
    private String jwtExpiryDate;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public ApiResponse addUser(SignUpDto signUpDto){
        Optional<UserEntity> userName = userRepository.findByUserName(signUpDto.getUserName());
        if(userName.isPresent())
            return USER_EXIST;

        UserEntity userEntity = new UserEntity();
        userEntity.setName(signUpDto.getName());
        userEntity.setUserName(signUpDto.getUserName());
        userEntity.setPassword(passwordEncoder.encode(signUpDto.getPassword()));


        if(signUpDto.getRoleEnum()== null){
            userEntity.setRoleEntityList(List.of(roleRepository.findByRoleEnum(RoleEnum.USER)));
        }
        else
            userEntity.setRoleEntityList(List.of(roleRepository.findByRoleEnum(signUpDto.getRoleEnum())));
        userRepository.save(userEntity);
        return SUCCESS;
    }

    public ApiResponse login(SignInDto signInDto){


        Optional<UserEntity> optionalUserEntity
                = userRepository.findByUserName(signInDto.getUserName());

        if (optionalUserEntity.isEmpty())
            return USER_NOT_FOUND;

        String token = this.generateToken(optionalUserEntity.get());
        SUCCESS.setData(token);

        return SUCCESS;
    }
    private String generateToken(UserEntity userEntity) {
        try {
            return Jwts.builder().signWith(SignatureAlgorithm.HS512, jwtSecretKey)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(jwtExpiryDate)))
                    .setSubject(userEntity.getUsername())
                    .compact();
        }catch (Exception e){
            return null;
        }


    }


}
