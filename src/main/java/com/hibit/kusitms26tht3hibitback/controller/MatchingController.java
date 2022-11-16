package com.hibit.kusitms26tht3hibitback.controller;


import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.dto.MatchingResponseDto;
import com.hibit.kusitms26tht3hibitback.dto.MatchingSaveRequestDto;
import com.hibit.kusitms26tht3hibitback.dto.MatchingUpdateRequestDto;
import com.hibit.kusitms26tht3hibitback.repository.MatchingRepository;
import com.hibit.kusitms26tht3hibitback.service.MatchingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;

import java.util.List;

@Tag(name="matching", description = "매칭 API")
@RestController
@RequestMapping("/matching")
@RequiredArgsConstructor

public class MatchingController {

    private final MatchingService matchingService;
    private final MatchingRepository matchingRepository;

    @PostMapping("/post")
    public int save(@RequestBody MatchingSaveRequestDto requestDto){
        //로그인한 유저 추가 필요
        return matchingService.save(requestDto);
    }

    @GetMapping("/")
    public List<MatchingResponseDto> findAll(@RequestParam final char deleteYn){
        return matchingService.findByDeleteYn(deleteYn);
    }

    @GetMapping("/{idx}")
    public MatchingResponseDto findById(@PathVariable int idx)
    {
        return matchingService.findById(idx);
    }

    @PutMapping("/edit/{idx}")
    public int update(@PathVariable int idx, @RequestBody MatchingUpdateRequestDto requestDto){
        return matchingService.update(idx, requestDto);
    }

    @DeleteMapping("/{idx}")
    public int delete(@PathVariable int idx){
        return matchingService.delete(idx);
    }
}

