package com.hibit.kusitms26tht3hibitback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hibit.kusitms26tht3hibitback.domain.Matching;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
public class MatchingSaveRequestDto {

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

    public Matching toEntity(){
        return Matching.builder()
                .title(title)
                .exhibition(exhibition)
                .content(content)
                .number(number)
                .start_date(start_date)
                .finish_date(finish_date)
                .end_date(end_date)
                .end(end)
                .view(view)
                .openchat(openchat)
                .want(want)
                .build();
    }
}
