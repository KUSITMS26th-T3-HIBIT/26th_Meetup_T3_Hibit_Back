package com.hibit.kusitms26tht3hibitback.dto;

import com.hibit.kusitms26tht3hibitback.domain.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter

public class UsermateResponseDto {
    private int idx;
    private String nickname;
    private String home;
    private boolean gender;
    private int style;
    private List<Integer> personality;
    private List<Integer> hobby;
    private double temperature;
    private String introduce;

    public UsermateResponseDto(Users entity){
        this.idx = entity.getIdx();
        this.nickname = entity.getNickname();
        this.home = entity.getHome();
        this.gender = entity.isGender();
        this.style = entity.getStyle();
        this.personality=entity.getPersonality();
        this.hobby=entity.getHobby();
        this.temperature=entity.getTemperature();
        this.introduce=entity.getIntroduce();
    }
}
