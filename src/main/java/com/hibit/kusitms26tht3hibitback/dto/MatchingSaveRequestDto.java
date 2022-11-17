package com.hibit.kusitms26tht3hibitback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Optional;

@Getter
public class MatchingSaveRequestDto {
    private Users user;
    private String title;
    private String exhibition;
    private String content;
    private int number;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate start_date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate finish_date;
    private boolean finish;
    private String openchat;
    private String want;

    public Matching toEntity(){
        return Matching.builder()
                .title(title)
                .exhibition(exhibition)
                .content(content)
                .number(number)
                .start_date(start_date)
                .finish_date(finish_date)
                .finish(finish)
                .openchat(openchat)
                .want(want)
                .build();
    }
    public void setUser(Users user) {
        this.user = user;
    }
}
