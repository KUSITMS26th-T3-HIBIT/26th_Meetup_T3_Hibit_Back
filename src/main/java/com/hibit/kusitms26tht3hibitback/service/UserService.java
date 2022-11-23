package com.hibit.kusitms26tht3hibitback.service;

import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.ProfileUpdateDto;
import com.hibit.kusitms26tht3hibitback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
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

    @Transactional
    public String update(String id, ProfileUpdateDto updateDto){
        Users user = userRepository.findUserById(id);
        user.update(updateDto.getPassword(),updateDto.getNickname(),updateDto.getPhone_number(), updateDto.getBirth(), updateDto.isGender(), updateDto.getEmail(), updateDto.getHome(),updateDto.getIntroduce(),updateDto.getStyle(),updateDto.getPersonality(), updateDto.getHobby());
        return id;
    }
}
