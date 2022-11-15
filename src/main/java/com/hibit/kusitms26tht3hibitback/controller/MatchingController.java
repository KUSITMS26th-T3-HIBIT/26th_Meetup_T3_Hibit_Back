package com.hibit.kusitms26tht3hibitback.controller;


import com.hibit.kusitms26tht3hibitback.dto.MatchingSaveRequestDto;
import com.hibit.kusitms26tht3hibitback.service.MatchingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name="matching", description = "매칭 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/matching")
public class MatchingController {

    private final MatchingService matchingService;

    @PostMapping("/posts")
    public int save(@RequestBody MatchingSaveRequestDto requestDto){
        //로그인한 유저 추가 필요
        return matchingService.save(requestDto);
    }
}
