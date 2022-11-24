package com.hibit.kusitms26tht3hibitback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
public class MatchingResponseDto {
    private int idx;
    private int user;
    @Schema(description = "제목", example = "함께 관람 하실 분 구합니다!")
    private String title;
    @Schema(description = "전시회 이름", example = "장 줄리앙 : 그러면, 거기")
    private String exhibition;
    @Schema(description = "내용", example = "장 줄리앙의 첫 회고전이자, 저의 첫 매칭~~")
    private String content;
    @Schema(description = "지역", example = "서울 강남구")
    private String area;
    @Schema(description = "인원수", example = "2")
    private int number;
    @Schema(description = "시작 날짜", example = "2022-11-10")
    private LocalDate start_date;
    @Schema(description = "마감 날짜", example = "2022-11-18")
    private LocalDate finish_date;
    @Schema(description = "모집마감 여부", example = "false")
    private boolean finish;
    @Column(nullable = false, columnDefinition = "integer default 0")
    private int view;
    @Schema(description = "오픈채팅 url", example = "https://openchat~~")
    private String openchat;
    @Schema(description = "원하는 메이트 정보", example = "2030여성으로, 수도권에 거주하시는 분~~")
    private String want;
    @Schema(description = "게시글 삭제 여부", example = "N")
    private char deleteYn;

    @Schema(description = "전시 관람 스타일", example = "1")
    private int style;


    public MatchingResponseDto(@NotNull Matching entity){
        this.idx= entity.getIdx();
        this.user = entity.getUser().getIdx();
        this.title=entity.getTitle();
        this.exhibition= entity.getExhibition();
        this.content= entity.getContent();
        this.area=entity.getArea();
        this.number= entity.getNumber();
        this.start_date=entity.getStart_date();
        this.finish_date=entity.getFinish_date();
        this.finish= entity.isFinish();
        this.view=entity.getView();
        this.openchat=entity.getOpenchat();
        this.want=entity.getWant();
        this.deleteYn = entity.getDeleteYn();
        this.style=entity.getUser().getStyle();
    }

}
