package com.hibit.kusitms26tht3hibitback.dto;

import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.Rating;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class RatingSaveRequestDto {
    private Users user;
    private Matching matching;
    private String nickname;
    private int mid;
    private double avg;
    private int question1;
    private int question2;
    private List<Integer> tag;

    @Builder
    public RatingSaveRequestDto(Users user, Matching matching, String nickname,
                                int mid, double avg, int question2, int question1, List<Integer> tag){
        this.user=user;
        this.matching=matching;
        this.nickname=nickname;
        this.mid=mid;
        this.avg=avg;
        this.question1=question1;
        this.question2=question2;
        this.tag=tag;
    }
    public Rating toEntity() {
        return Rating.builder()
                .user(user)
                .matching(matching)
                .nickname(nickname)
                .mid(mid)
                .avg(avg)
                .question1(question1)
                .question2(question2)
                .tag(tag)
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
    public void setAvg(){this.avg=(this.question1+this.question2)/2;}

}
