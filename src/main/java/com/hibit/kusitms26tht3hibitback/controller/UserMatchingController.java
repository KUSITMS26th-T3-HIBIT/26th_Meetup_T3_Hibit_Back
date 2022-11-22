package com.hibit.kusitms26tht3hibitback.controller;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.UserMatchingResponseDto;
import com.hibit.kusitms26tht3hibitback.service.MatchingService;
import com.hibit.kusitms26tht3hibitback.service.UserMatchingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "usermatching", description = "매칭 신청 API")
@RequestMapping("/matching")
@RequiredArgsConstructor
@RestController
public class UserMatchingController {
    private final UserMatchingService userMatchingService;



    @GetMapping("{idx}/application")
    @Operation(summary = "matching/{idx}/application", description = "매칭메이트 신청")
    public Map<String, Object> saveUserMatching(@PathVariable int idx, Authentication authentication){
        //중복 신청 검사는 아직 안함..
        Matching matching = userMatchingService.findMatchingById(idx);
        Users user = (Users) authentication.getPrincipal();
        Map<String, Object> response = new HashMap<>();
        if (matching.getUser().getIdx() == user.getIdx()){
            response.put("result", "자신이 쓴 글은 신청할 수 없습니다.");
        }
        else{
            //이 경우에 알림 요청하는거 추가해야함
            idx = userMatchingService.saveUserMatching(user, matching);
            response.put("idx",idx);
        }
        return response;
    }
    @GetMapping("{idx}/participants")
    @Operation(summary = "matching/{idx}/participants", description = "매칭디테일 참여자 정보")
    public List<UserMatchingResponseDto> findByMatching(@PathVariable int idx){

        Matching matching = userMatchingService.findMatchingById(idx);
        return userMatchingService.findByMatching(matching);
    }





}
