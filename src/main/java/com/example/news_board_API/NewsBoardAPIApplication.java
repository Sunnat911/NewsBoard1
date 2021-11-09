package com.example.news_board_API;

import com.example.news_board_API.entity.RoleEntity;
import com.example.news_board_API.entity.UserEntity;
import com.example.news_board_API.enums.RoleEnum;
import com.example.news_board_API.repository.RoleRepository;
import com.example.news_board_API.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class NewsBoardAPIApplication implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String first;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    public static void main(String[] args) {
        SpringApplication.run(NewsBoardAPIApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        if(first.equals("always")) {
            RoleEntity user =new RoleEntity();
            user.setRoleEnum(RoleEnum.USER);
            roleRepository.save(user);

            RoleEntity admin =new RoleEntity();
            admin.setRoleEnum(RoleEnum.ADMIN);
            roleRepository.save(admin);

            UserEntity root=new UserEntity();
            root.setUserName("root");
            root.setPassword(passwordEncoder.encode("root"));
            root.setRoleEntityList(List.of(admin));
            userRepository.save(root);



    }
    }
}
