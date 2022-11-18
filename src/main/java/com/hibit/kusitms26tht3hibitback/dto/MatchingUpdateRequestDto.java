package com.hibit.kusitms26tht3hibitback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hibit.kusitms26tht3hibitback.domain.Matching;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MatchingUpdateRequestDto {
    @Schema(description = "제목", example = "함께 관람 하실 분 구합니다!")
    private String title;
    @Schema(description = "전시회 이름", example = "장 줄리앙 : 그러면, 거기")
    private String exhibition;
    @Schema(description = "내용", example = "장 줄리앙의 첫 회고전이자, 저의 첫 매칭~~")
    private String content;
    @Schema(description = "전시 카테고리", example = "1")
    private int category;
    @Schema(description = "인원수", example = "2")
    private int number;
    @Schema(description = "시작 날짜", example = "2022-11-10")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate start_date;
    @Schema(description = "마감 날짜", example = "2022-11-18")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate finish_date;
    @Schema(description = "오픈채팅 url", example = "https://openchat~~")
    private String openchat;
    @Schema(description = "원하는 메이트 정보", example = "2030여성으로, 수도권에 거주하시는 분~~")
    private String want;


    @Builder
    public MatchingUpdateRequestDto(String title, String exhibition, String content,int category, int number, LocalDate start_date, LocalDate finish_date, String openchat, String want){
        this.title= title;
        this.exhibition= exhibition;
        this.content= content;
        this.category=category;
        this.number= number;
        this.start_date= start_date;
        this.finish_date=finish_date;
        this.openchat=openchat;
        this.want=want;
    }
}
