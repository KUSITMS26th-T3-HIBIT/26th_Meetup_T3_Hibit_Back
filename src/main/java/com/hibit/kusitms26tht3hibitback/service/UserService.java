package com.hibit.kusitms26tht3hibitback.service;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.Posts;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.ProfileResponseDto;
import com.hibit.kusitms26tht3hibitback.dto.ProfileUpdateDto;
import com.hibit.kusitms26tht3hibitback.repository.MatchingRepository;
import com.hibit.kusitms26tht3hibitback.repository.PostsRepository;
import com.hibit.kusitms26tht3hibitback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MatchingService matchingService;

    @Autowired
    PostsService postsService;

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

    @Transactional
    public ProfileResponseDto  findUserProfile(String id){
        Users user = userRepository.findUserById(id);
        List<Matching> matchingList = matchingService.search(id);
        List<Posts> postsList = postsService.search(id);
        return new ProfileResponseDto(user.getId(), user.getNickname(),1,user.getTemperature(),matchingList,postsList);
    }
}
