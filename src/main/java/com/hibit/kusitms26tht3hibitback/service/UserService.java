package com.hibit.kusitms26tht3hibitback.service;

import com.hibit.kusitms26tht3hibitback.domain.User;
import com.hibit.kusitms26tht3hibitback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User insertUser(User user){
        return userRepository.save(user);
    }

    public boolean existId(String id){
        return userRepository.existsById(id);
    }
}
