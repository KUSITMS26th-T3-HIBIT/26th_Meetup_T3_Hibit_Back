package com.hibit.kusitms26tht3hibitback.controller;

import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.hibit.kusitms26tht3hibitback.dto.MatchingResponseDto;
import com.hibit.kusitms26tht3hibitback.dto.PostResponseDto;
import com.hibit.kusitms26tht3hibitback.dto.PostUpdateRequestDto;
import com.hibit.kusitms26tht3hibitback.dto.PostsSaveRequestDto;
import com.hibit.kusitms26tht3hibitback.service.PostsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "posts", description = "커뮤니티 게시글")
@RestController
@RequestMapping("/community")
public class PostController {
    @Autowired
    private PostsService postsService;

    @PostMapping("/post")
    @Secured({"ROLE_USER"})

    @Operation(summary = "community/post", description = "커뮤니티 게시글 작성")
    @Parameters({@Parameter(name = "title", description = "제목", example = "디뮤지엄 전시 후기에요"),
            @Parameter(name = "content", description = "내용", example = "3층까지 있었고,,, 사진이,,,"),
            @Parameter(name = "file", description = "파일", example = "사진 url 아 여러개면 배열로 바꿔야겠다...")
    })
    public int save(@RequestBody PostsSaveRequestDto requestDto, Authentication authentication){
        Users user = (Users) authentication.getPrincipal();
        return postsService.save(requestDto, user);
    }

    @GetMapping("/list")
    @Operation(summary = "community/list", description = "커뮤니티 글 리스트")
    public List<PostResponseDto> findById(){
        char flag = 'N';
        return postsService.findByDeleteYn(flag);
    }

    @GetMapping("/{idx}")
    @Operation(summary = "community/{idx}", description = "커뮤니티 글 상세")
    public PostResponseDto findById(@PathVariable int idx){
        return postsService.findById(idx);
    }

    @PutMapping("/edit/{idx}")
    @Operation(summary = "community/edit/{idx}",description = "커뮤니티 글 수정")
    public int update(@PathVariable int idx, @RequestBody PostUpdateRequestDto requestDto){
        return postsService.update(idx, requestDto);
    }

    @DeleteMapping("/{idx}")
    @Operation(summary = "community/{idx}", description = "커뮤니티 글 삭제")
    public int delete(@PathVariable int idx){
        return postsService.delete(idx);
    }

    @GetMapping("/search/{keyword}")
    public List<PostResponseDto> searchMatching(@Parameter(name = "keyword", description = "검색 키워드", in = ParameterIn.PATH)@PathVariable String keyword) {
        return postsService.searchMatching(keyword);

    }
}
