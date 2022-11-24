package com.hibit.kusitms26tht3hibitback.service;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.UserMatching;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.MatchingResponseDto;
import com.hibit.kusitms26tht3hibitback.dto.UserMatchingResponseDto;
import com.hibit.kusitms26tht3hibitback.dto.UserMatchingSaveDto;
import com.hibit.kusitms26tht3hibitback.dto.UserMatchingUpdateDto;
import com.hibit.kusitms26tht3hibitback.repository.MatchingRepository;
import com.hibit.kusitms26tht3hibitback.repository.UserMatchingRepository;
import com.hibit.kusitms26tht3hibitback.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserMatchingService {
    private final UserMatchingRepository userMatchingRepository;
    private final MatchingRepository matchingRepository;

    public Matching findMatchingById(int idx){
        Matching entity = matchingRepository.findById(idx).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+idx));
        return entity;
    }

    public List<UserMatchingResponseDto> findByMatching(Matching matching){
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        List<UserMatching> list = userMatchingRepository.findByMatching(matching, sort);
        return list.stream().map(UserMatchingResponseDto::new).collect(Collectors.toList());
    }

    public boolean existsByNickname(String nickname){
        return userMatchingRepository.existsByNickname(nickname);
    }

    @Transactional
    public UserMatching saveUserMatching(UserMatchingSaveDto userMatchingSaveDto, Users user, Matching matching){
        userMatchingSaveDto.setUser(user);
        userMatchingSaveDto.setMatching(matching);
        userMatchingSaveDto.setUserNickname(user);
        userMatchingSaveDto.setMatchingId(matching);
        userMatchingSaveDto.setWriter();
        UserMatching userMatching = userMatchingSaveDto.toEntity();
        userMatchingRepository.save(userMatching);
        return userMatching;
    }

    @Transactional
    public UserMatching update(int idx, String nickname, UserMatchingUpdateDto userMatchingUpdateDto){
        UserMatching userMatching = userMatchingRepository.findByMidAndNickname(idx,nickname);
        //.orElseThrow(()-> new IllegalArgumentException("해당 신청이 없습니다."));
        userMatching.update(userMatchingUpdateDto.getMatching_check(),
                userMatchingUpdateDto.getEvaluation_check());
        return userMatching;
    }
//
//    @Transactional
//    public UserMatching updateEvaluation(int idx, String nickname, UserMatchingUpdateDto userMatchingUpdateDto){
//        UserMatching userMatching = userMatchingRepository.findByMidAndNickname(idx,nickname);
//        char rating = 'T';
//        userMatchingUpdateDto.setEvaluation_check(rating);
//        userMatchingRepository.save(userMatching);
//        return userMatching;
//    }
//
}
