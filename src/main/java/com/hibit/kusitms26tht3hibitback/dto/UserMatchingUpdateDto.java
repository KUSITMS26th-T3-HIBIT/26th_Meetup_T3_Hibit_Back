package com.hibit.kusitms26tht3hibitback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserMatchingUpdateDto {
    private char matching_check;
    private char evaluation_check;

    @Builder
    public UserMatchingUpdateDto(char matching_check, char evaluation_check){
        this.matching_check = matching_check;
        this.evaluation_check = evaluation_check;
    }
}
