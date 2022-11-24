package com.hibit.kusitms26tht3hibitback.dto;

import com.hibit.kusitms26tht3hibitback.domain.Alarm;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class AlarmResponseDto {
    private int idx;
    private int user;
    @Schema(description = "자신 닉네임", example = "false")
    private String nickname;
    @Schema(description = "알림 타입", example = "M/T/F")
    private char category;
    @Schema(description = "알림 내용", example = "__님이 매칭을 신청했습니다.")
    private String content;
    @Schema(description = "읽음 여부", example = "N")
    private char readed;
    @Schema(description = "오픈채팅 url", example = "http://openchat")
    private String openchat;

    public AlarmResponseDto(Alarm entity){
        this.idx =entity.getIdx();
        this.user=entity.getUser().getIdx();
        this.nickname=entity.getNickname();
        this.category=entity.getCategory();
        this.content=entity.getContent();
        this.readed=entity.getReaded();
        this.openchat=entity.getOpenchat();
    }
}
