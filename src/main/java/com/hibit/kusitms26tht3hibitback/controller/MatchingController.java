package com.hibit.kusitms26tht3hibitback.controller;


import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.MatchingResponseDto;
import com.hibit.kusitms26tht3hibitback.dto.MatchingSaveRequestDto;
import com.hibit.kusitms26tht3hibitback.dto.MatchingUpdateRequestDto;
import com.hibit.kusitms26tht3hibitback.dto.UsermateResponseDto;
import com.hibit.kusitms26tht3hibitback.global.util.SecurityUtil;
import com.hibit.kusitms26tht3hibitback.repository.MatchingRepository;
import com.hibit.kusitms26tht3hibitback.repository.UserRepository;
import com.hibit.kusitms26tht3hibitback.service.MatchingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Tag(name="matching", description = "매칭 API")
@RestController
@RequestMapping("/matching")

public class MatchingController {

    @Autowired
    private MatchingService matchingService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/post")
    @Secured({"ROLE_USER"})

    @Operation(summary = "matching/post", description = "매칭글작성")
    @Parameters({@Parameter(name = "title",description="제목",example = "전시회보러가요"),
            @Parameter(name = "exhibition",description="전시회이름",example = "디뮤지엄 어쩌다 사랑"),
            @Parameter(name = "content",description="내용",example = "전시회 처음 보러가는데,~~~~"),
            @Parameter(name = "category",description="전시카테고리",example = "1"),
            @Parameter(name = "number",description="명수",example = "2"),
            @Parameter(name = "start_date",description="시작 날짜",example = "2022-11-15"),
            @Parameter(name = "end_date",description="마감 날짜",example = "2022-11-19"),
            @Parameter(name = "end",description="마감여부",example = "0"),
            @Parameter(name = "openchat",description="옾챗url",example = "http:/open"),
            @Parameter(name = "want",description="원하는 메이트",example = "말 적은 사람이랑, 사진 잘 찍는사람이랑 가고싶어요")
    })
    public int save(@RequestBody MatchingSaveRequestDto requestDto, Authentication authentication){
        //로그인 한 유저
        Users user = (Users) authentication.getPrincipal();
        //글 작성 정보 저장
        return matchingService.save(requestDto, user);
    }

    @GetMapping("/list")
    @Operation(summary = "matching/list", description = "매칭글 리스트")
    public List<MatchingResponseDto> findAll(){
        //deleteYn = N인 게시글 리스트 가져오기
        char flag = 'N';
        return matchingService.findByDeleteYn(flag);
    }

    @GetMapping("/{idx}")
    @Operation(summary = "matching/{Idx}", description = "매칭글 세부 페이지")
    public MatchingResponseDto findById(@PathVariable int idx)
    {
        //매칭글 세부페이지
        return matchingService.findById(idx);
    }
    //매칭메이트 메이트 세부페이지 -> api따로
    @GetMapping("/{idx}/mate")
    @Operation(summary = "matching/{idx}/mate", description = "매칭메이트 세부 페이지")
    public UsermateResponseDto findUserById(@PathVariable int idx)
    {
        return matchingService.findUserById(idx);
    }



    @PutMapping("/edit/{idx}")
    @Operation(summary = "matching/edit/{idx}", description = "매칭글 수정")
    public int update(@PathVariable int idx, @RequestBody MatchingUpdateRequestDto requestDto){
        return matchingService.update(idx, requestDto);
    }

    @DeleteMapping("/{idx}")
    @Operation(summary = "matching/{idx}", description = "매칭글 삭제")
    public int delete(@PathVariable int idx){
        return matchingService.delete(idx);
    }

}

