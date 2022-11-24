package com.hibit.kusitms26tht3hibitback.controller;

import com.hibit.kusitms26tht3hibitback.domain.Alarm;
import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.UserMatching;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.*;
import com.hibit.kusitms26tht3hibitback.service.AlarmService;
import com.hibit.kusitms26tht3hibitback.service.MatchingService;
import com.hibit.kusitms26tht3hibitback.service.UserMatchingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "usermatching", description = "매칭 신청 API")
@RequestMapping("/matching")
@RequiredArgsConstructor
@RestController
public class UserMatchingController {
    private final UserMatchingService userMatchingService;
    private final AlarmService alarmService;

    @Parameters({@Parameter(name = "matching_check",description="매칭 수락 여부",example = "Y"),
            @Parameter(name = "evaluation_check",description="평가 여부",example = "W")})

    @PostMapping("{idx}/application")
    @Operation(summary = "matching/{idx}/application", description = "매칭메이트 신청")
    public Map<String, Object> saveUserMatching(@PathVariable int idx, @RequestBody UserMatchingSaveDto requestDto, Authentication authentication){
        Matching matching = userMatchingService.findMatchingById(idx);
        Users user = (Users) authentication.getPrincipal();
        Map<String, Object> response = new HashMap<>();
        if (matching.getUser().getIdx() == user.getIdx()){
            response.put("result", "자신이 쓴 글은 신청할 수 없습니다.");
        }
        else{
            if(userMatchingService.existsByNickname(user.getNickname())==false){
                UserMatching userMatching;
                userMatching = userMatchingService.saveUserMatching(requestDto, user, matching);
                alarmService.saveMatching(userMatching.getWriter(), idx, user);
                response.put("idx",userMatching);
            }
            else{
                response.put("result", "이미 신청한 글입니다.");
            }
        }
        return response;
    }


    @GetMapping("{idx}/participants")
    @Operation(summary = "matching/{idx}/participants", description = "매칭디테일 참여자 정보")
    public Map<String, Object> findByMatching(@PathVariable int idx, Authentication authentication){
        Matching matching = userMatchingService.findMatchingById(idx);
        Users user = (Users) authentication.getPrincipal();
        Map<String, Object> response = new HashMap<>();

        if (matching.getUser().getIdx() == user.getIdx()) {
            List list = userMatchingService.findByMatching(matching);
            response.put("List", list);
        }
        else{
            response.put("result", "작성한 유저가 아닙니다.");
        }
        return response;
    }

    @PutMapping("{idx}/participants")
    @Operation(summary = "matching/{idx}/participants", description = "수락/거절/평가")
    public char update(@PathVariable int idx, @RequestParam String nickname, @RequestBody UserMatchingUpdateDto userMatchingUpdateDto){
        UserMatching userMatching;
        userMatching = userMatchingService.update(idx, nickname, userMatchingUpdateDto);
        alarmService.saveMatchingTF(nickname, idx);
        return userMatching.getMatching_check();
    }
}
