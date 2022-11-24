package com.hibit.kusitms26tht3hibitback.dto;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.Posts;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class ProfileResponseDto {

    @Schema(description = "유저 아이디", example = "mangolover")
    private String id;

    @Schema(description = "유저 이름", example = "망고피자")
    private String nickname;

    private int matCount;


    private double temperature;

    private List<Matching> matchingList;

    private List<Posts> postsList;

    @Builder
    public ProfileResponseDto (String id, String nickname, int matCount, double temperature, List<Matching> matchingList, List<Posts> postsList){
        this.id = id;
        this.nickname = nickname;
        this.matCount = matCount;
        this.temperature = temperature;
        this.matchingList = matchingList;
        this.postsList = postsList;
    }
}
