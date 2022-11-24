package com.hibit.kusitms26tht3hibitback.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hibit.kusitms26tht3hibitback.service.UserMatchingService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name ="rating")
public class Rating {
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
    private double avg;

    @Column(nullable = false)
    private int question1;

    @Column(nullable = false)
    private int question2;

    @ElementCollection
    @Schema(description = "태그")
    private List<Integer> tag;

    @Builder
    public Rating(Users user, Matching matching,
                  String nickname, int mid,
                  double avg, int question1, int question2, List<Integer> tag){
        this.user=user;
        this.matching=matching;
        this.nickname=nickname;
        this.mid=mid;
        this.avg=avg;
        this.question1=question1;
        this.question2=question2;
        this.tag=tag;
    }

}
