package com.hibit.kusitms26tht3hibitback.dto;

import com.hibit.kusitms26tht3hibitback.domain.Alarm;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.PublicKey;

@NoArgsConstructor
@Getter
public class AlarmSaveRequestDto {
    private Users user;
    @Schema(description = "자신 닉네임", example = "닉네임")
    private String nickname;
    @Schema(description = "알림 타입", example = "M")
    private char category;
    @Schema(description = "알림 내용", example = "매칭이 신청이 있습니다.")
    private String content;
    @Schema(description = "읽음", example = "N")
    private char readed;
    @Schema(description = "매칭글 Idx", example = "1")
    private int mid;
    @Schema(description = "오픈채팅 링크", example = "http://openchat")
    private String openchat;

    @Builder
    public AlarmSaveRequestDto(Users user, String nickname, char category, String content, int mid, char readed, String openchat){
        this.user = user;
        this.nickname= nickname;
        this.category=category;
        this.content = content;
        this.mid=mid;
        this.readed=readed;
        this.openchat=openchat;
    }

    public Alarm toEntity(){
        return Alarm.builder()
                .user(user)
                .nickname(nickname)
                .category(category)
                .mid(mid)
                .content(content)
                .readed(readed)
                .openchat(openchat)
                .build();
    }

    public void setUser(Users user) {
        this.user = user;
    }
    public void setNickname(String nickname) {this.nickname=nickname;}
    public void setCategory(){this.category = 'M';}
    public void setCategoryT(){this.category = 'T';}
    public void setCategoryF(){this.category = 'F';}
    public void setReaded(){this.readed='N';}
    public void setMid(int mid){this.mid=mid;}
    public void setContent(String content){this.content=content;}
    public void setOpenchat(String open){this.openchat = open;}
}
