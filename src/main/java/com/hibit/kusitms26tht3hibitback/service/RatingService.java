package com.hibit.kusitms26tht3hibitback.service;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.Rating;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.MatchingSaveRequestDto;
import com.hibit.kusitms26tht3hibitback.dto.RatingSaveRequestDto;
import com.hibit.kusitms26tht3hibitback.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class RatingService {
    private final RatingRepository ratingRepository;


    @Transactional
    public int save(RatingSaveRequestDto requestDto, Users user, Matching matching){
        requestDto.setUser(user);
        requestDto.setMatching(matching);
        requestDto.setUserNickname(user);
        requestDto.setMatchingId(matching);
        requestDto.setAvg();
        Rating rating = requestDto.toEntity();
        ratingRepository.save(rating);
        return rating.getIdx();
    }
}
