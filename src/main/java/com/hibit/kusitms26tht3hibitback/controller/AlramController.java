package com.hibit.kusitms26tht3hibitback.controller;

import com.hibit.kusitms26tht3hibitback.domain.Alarm;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.AlarmResponseDto;
import com.hibit.kusitms26tht3hibitback.dto.MatchingSaveRequestDto;
import com.hibit.kusitms26tht3hibitback.service.AlarmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Tag(name="alarm", description = "알람 api")
@RestController
@RequestMapping("/alarm")
@RequiredArgsConstructor
public class AlramController {
    private final AlarmService alarmService;

    @GetMapping("/list")
    @Operation(summary = "alarm", description = "알람 목록")
    public List<AlarmResponseDto> findAll(Authentication authentication){
        Users user = (Users) authentication.getPrincipal();
        String nickname=user.getNickname();
        char flag ='N';
        return alarmService.findByReadedAndNickname(flag, nickname);
    }


    @GetMapping("/{idx}")
    @Operation(summary = "alarm", description = "알람 세부")
    public Map<String, Object> findById(@PathVariable int idx){
        return alarmService.findById(idx);
    }

}
