package com.hibit.kusitms26tht3hibitback.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hibit.kusitms26tht3hibitback.BaseTimeEntity;
import com.hibit.kusitms26tht3hibitback.dto.MatchingResponseDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name ="usermatching")
public class UserMatching extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private Users user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matching_idx")
    private Matching matching;

    @Column(nullable = false)
    private String nickname;
    @Column(nullable = false)
    private int mid;

    @Column(nullable = false)
    private int writer;

    @Column(nullable = false)
    private char matching_check;

    @Column(nullable = true)
    private char evaluation_check;

    @Builder
    public UserMatching(Users user, Matching matching,
                        String nickname, int mid,
                        int writer, char matching_check, char evaluation_check){
        this.user =user;
        this.matching=matching;
        this.nickname=nickname;
        this.mid = mid;
        this.writer = writer;
        this.matching_check=matching_check;
        this.evaluation_check=evaluation_check;
    }

    public void update(char matching_check, char evaluation_check){
        this.matching_check = matching_check;
        this.evaluation_check = evaluation_check;
    }

}
