package com.hibit.kusitms26tht3hibitback.controller;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.MatchingSaveRequestDto;
import com.hibit.kusitms26tht3hibitback.dto.RatingSaveRequestDto;
import com.hibit.kusitms26tht3hibitback.service.RatingService;
import com.hibit.kusitms26tht3hibitback.service.UserMatchingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name="rating", description = "평가 API")
@RequestMapping("/rating")
@RestController
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;
    private final UserMatchingService userMatchingService;

    @PostMapping("/{idx}")

    public int save(@PathVariable int idx, @RequestBody RatingSaveRequestDto requestDto, Authentication authentication){
        //로그인 한 유저
        Users user = (Users) authentication.getPrincipal();
        Matching matching = userMatchingService.findMatchingById(idx);

        //글 작성 정보 저장
        return ratingService.save(requestDto, user, matching);
        //실제 점수에 반영될려면 유저매칭 신청 테이블, 유저테이블이랑 연관지어야함.. 이거안해도되겠지..?
        //Evaluation T로 변환

    }


}
