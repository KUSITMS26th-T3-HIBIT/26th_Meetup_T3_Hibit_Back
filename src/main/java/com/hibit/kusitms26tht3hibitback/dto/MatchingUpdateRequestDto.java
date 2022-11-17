package com.hibit.kusitms26tht3hibitback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hibit.kusitms26tht3hibitback.domain.Matching;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MatchingUpdateRequestDto {

    private String title;
    private String exhibition;
    private String content;
    private int number;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate start_date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate finish_date;
    private String openchat;
    private String want;


    @Builder
    public MatchingUpdateRequestDto(String title, String exhibition, String content, int number, LocalDate start_date, LocalDate finish_date, String openchat, String want){
        this.title= title;
        this.exhibition= exhibition;
        this.content= content;
        this.number= number;
        this.start_date= start_date;
        this.finish_date=finish_date;
        this.openchat=openchat;
        this.want=want;
    }
}
