package com.example.news_board_API.service;




import com.example.news_board_API.entity.UserEntity;
import com.example.news_board_API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ApplicationUserDetailService implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public ApplicationUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> optionalUserEntity = userRepository.findByUserName(username);
        if (optionalUserEntity.isEmpty())
            throw new UsernameNotFoundException(username + " user not found ");

        return optionalUserEntity.get();
    }
}
