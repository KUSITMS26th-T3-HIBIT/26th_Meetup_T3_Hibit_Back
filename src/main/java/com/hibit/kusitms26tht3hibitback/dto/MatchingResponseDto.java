package com.hibit.kusitms26tht3hibitback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
public class MatchingResponseDto {
    private int idx;
    private Users user;
    private String title;
    private String exhibition;
    private String content;
    private int number;
    private LocalDate start_date;
    private LocalDate finish_date;
    private boolean finish;
    private int view;
    private String openchat;
    private String want;
    private char deleteYn;

    public MatchingResponseDto(@NotNull Matching entity){
        this.idx= entity.getIdx();
        this.user = entity.getUser();
        this.title=entity.getTitle();
        this.exhibition= entity.getExhibition();
        this.content= entity.getContent();
        this.number= entity.getNumber();
        this.start_date=entity.getStart_date();
        this.finish_date=entity.getFinish_date();
        this.finish= entity.isFinish();
        this.view=entity.getView();
        this.openchat=entity.getOpenchat();
        this.want=entity.getWant();
        this.deleteYn = entity.getDeleteYn();
    }

}
