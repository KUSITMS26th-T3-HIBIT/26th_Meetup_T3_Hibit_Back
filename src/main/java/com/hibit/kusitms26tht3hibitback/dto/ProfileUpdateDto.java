package com.hibit.kusitms26tht3hibitback.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import java.util.List;

@Getter
@NoArgsConstructor
public class ProfileUpdateDto {
    @Schema(description = "비밀번호", example = "1234")
    private String password;

    @Schema(description = "닉네임", example = "아린")
    private String nickname;

    @Schema(description = "전화번호", example = "01011112222")
    private String phone_number;

    @Schema(description = "생년월일")
    private String birth;

    @Schema(description = "성별", example = "True")
    private boolean gender;

    @Schema(description = "이메일")
    private String email;

    @Schema(description = "거주지", example = "경기도")
    private String home;

    @Schema(description = "자기소개", example = "안녕")
    private String introduce;

    @Schema(description = "관람 스타일")
    private int style;

    @Schema(description = "성격")
    private List<Integer> personality;

    @Schema(description = "취미")
    private List<Integer> hobby;

    @Builder
    public ProfileUpdateDto(String password, String nickname, String phone_number, String birth, boolean gender, String email, String home, String introduce, int style, List<Integer> personality, List<Integer> hobby){
        this.password = password;
        this.nickname = nickname;
        this.phone_number = phone_number;
        this.birth = birth;
        this.gender = gender;
        this.email = email;
        this.home = home;
        this.introduce = introduce;
        this.style = style;
        this.personality = personality;
        this.hobby = hobby;
    }

}
