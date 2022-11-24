package com.hibit.kusitms26tht3hibitback.dto;

import com.hibit.kusitms26tht3hibitback.domain.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserMatchingUpdateDto {
    @Schema(description = "매칭 수락 여부", example = "T")
    private char matching_check;
    @Schema(description = "평가 가능 여부", example = "T")
    private char evaluation_check;

    @Builder
    public UserMatchingUpdateDto(char matching_check, char evaluation_check){
        this.matching_check = matching_check;
        this.evaluation_check = evaluation_check;
    }
    public void setEvaluation_check(char rating) {
        this.evaluation_check = rating;
    }
}
