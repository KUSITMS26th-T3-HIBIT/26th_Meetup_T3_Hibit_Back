package com.hibit.kusitms26tht3hibitback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hibit.kusitms26tht3hibitback.domain.Matching;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
public class MatchingResponseDto {
    private int idx;
    private int user;
    private String title;
    private String exhibition;
    private String content;
    private int number;
    private LocalDateTime start_date;
    private LocalDateTime finish_date;
    private LocalDateTime end_date;
    private boolean end;
    private int view;
    private String openchat;
    private String want;
    private char deleteYn;

    public MatchingResponseDto(Matching matching){
        this.idx= matching.getIdx();
        this.user = matching.getUser().getIdx();
        this.title=matching.getTitle();
        this.exhibition= matching.getExhibition();
        this.content= matching.getContent();
        this.number= matching.getNumber();
        this.start_date=matching.getStart_date();
        this.finish_date=matching.getFinish_date();
        this.end_date=matching.getEnd_date();
        this.end= matching.isEnd();
        this.view=matching.getView();
        this.openchat=matching.getOpenchat();
        this.want=matching.getWant();
        this.deleteYn = matching.getDeleteYn();
    }

}
