package com.hibit.kusitms26tht3hibitback.dto;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.UserMatching;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserMatchingSaveDto {
    private Users user;
    private Matching matching;
    private String nickname;
    private int mid;
    private int writer;
    @Schema(description = "수락 여부", example = "W")
    private char matching_check;
    @Schema(description = "평가 여부", example = "W")
    private char evaluation_check;

    @Builder
    public UserMatchingSaveDto(Users users, Matching matching,String nickname, int mid, int writer, char matching_check,char evaluation_check){
        this.user=users;
        this.matching=matching;
        this.nickname=nickname;
        this.mid=mid;
        this.writer= writer;
        this.matching_check=matching_check;
        this.evaluation_check=evaluation_check;
    }

    public UserMatching toEntity(){
        return UserMatching.builder()
                .user(user)
                .matching(matching)
                .nickname(nickname)
                .mid(mid)
                .writer(writer)
                .matching_check(matching_check)
                .evaluation_check(evaluation_check)
                .build();
    }

    public void setUser(Users user) {
        this.user = user;
    }
    public void setMatching(Matching matching) {
        this.matching = matching;
    }
    public void setUserNickname(Users user) {this.nickname = user.getNickname();}
    public void setMatchingId(Matching matching) {this.mid = matching.getIdx();}
    public void setWriter() {this.writer = matching.getIdx();}

}
