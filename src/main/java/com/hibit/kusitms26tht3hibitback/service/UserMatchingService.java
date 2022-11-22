package com.hibit.kusitms26tht3hibitback.service;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.UserMatching;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.MatchingResponseDto;
import com.hibit.kusitms26tht3hibitback.dto.UserMatchingResponseDto;
import com.hibit.kusitms26tht3hibitback.dto.UserMatchingSaveDto;
import com.hibit.kusitms26tht3hibitback.repository.MatchingRepository;
import com.hibit.kusitms26tht3hibitback.repository.UserMatchingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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

    @Transactional
    public int saveUserMatching(Users user, Matching matching){
        UserMatchingSaveDto userMatchingSaveDto = new UserMatchingSaveDto();
        userMatchingSaveDto.setUser(user);
        userMatchingSaveDto.setMatching(matching);
        userMatchingSaveDto.setAccept();
        userMatchingSaveDto.setEvaluation_check();
        userMatchingSaveDto.setWriter();
        UserMatching userMatching = userMatchingSaveDto.toEntity();
        userMatchingRepository.save(userMatching);
        return userMatching.getIdx();
    }

}
