package com.hibit.kusitms26tht3hibitback.service;

import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Users insertUser(Users users){
        return userRepository.save(users);
    }

    public boolean existId(String id){
        return userRepository.existsById(id);
    }

    public boolean existNickname(String nickname){
        return userRepository.existsByNickname(nickname);
    }

}
