package com.hibit.kusitms26tht3hibitback.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDto {

    @Schema(description = "아이디", example = "arin123")
    private String id;

    @Schema(description = "비밀번호", example = "1234")
    private String password;

    @Schema(description = "닉네임", example = "아린")
    private String nickname;

    @Schema(description = "전화번호", example = "01011112222")
    private String phone_number;

    @Schema(description = "나이", example = "24")
    private int age;

    @Schema(description = "성별", example = "True")
    private boolean gender;

    @Schema(description = "거주지", example = "경기도")
    private String home;

    @Schema(description = "자기소개", example = "안녕")
    private String introduce;

    public SignUpDto(String id, String password, String nickname, String phone_number, int age, boolean gender, String home, String introduce){
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.phone_number = phone_number;
        this.age = age;
        this.gender = gender;
        this.home = home;
        this.introduce = introduce;
    }
}
