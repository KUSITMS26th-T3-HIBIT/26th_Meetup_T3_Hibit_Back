package com.hibit.kusitms26tht3hibitback.controller;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.UserMatching;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.UserMatchingResponseDto;
import com.hibit.kusitms26tht3hibitback.dto.UserMatchingUpdateDto;
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

    @Parameters({@Parameter(name = "matching_check",description="매칭 수락 여부",example = "Y"),
            @Parameter(name = "evaluation_check",description="평가 여부",example = "W")})

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

//    @GetMapping("{idx}/participants")
//    @Operation(summary = "matching/{idx}/participants", description = "매칭디테일 참여자 정보")
//    public List<UserMatchingResponseDto> findByMatching(@PathVariable int idx, Authentication authentication){
//        Matching matching = userMatchingService.findMatchingById(idx);
//        Users user = (Users) authentication.getPrincipal();
//        if (matching.getUser().getIdx() == user.getIdx()) {
//            return userMatchingService.findByMatching(matching);
//        }
//        else{
//            // 아직...
//        }
//    }


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
    public UserMatching update(@PathVariable int idx, @RequestParam String nickname, @RequestBody UserMatchingUpdateDto userMatchingUpdateDto){
        return userMatchingService.update(idx, nickname, userMatchingUpdateDto);
    }
}
