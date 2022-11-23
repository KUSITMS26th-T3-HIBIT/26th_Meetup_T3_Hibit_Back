package com.hibit.kusitms26tht3hibitback.dto;

import com.hibit.kusitms26tht3hibitback.domain.UserMatching;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserMatchingResponseDto {

    private int idx;
    private String user;
    private int matching;
    private int writer;
    private char matching_check;
    private char evaluation_check;

    public UserMatchingResponseDto(UserMatching entity){
        this.idx = entity.getIdx();
        this.user = entity.getUser().getNickname();
        this.matching = entity.getMatching().getIdx();
        this.writer=entity.getWriter();
        this.matching_check= entity.getMatching_check();
        this.evaluation_check=entity.getEvaluation_check();
    }
}
