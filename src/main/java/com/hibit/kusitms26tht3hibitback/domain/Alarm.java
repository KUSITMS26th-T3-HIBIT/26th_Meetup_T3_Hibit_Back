package com.hibit.kusitms26tht3hibitback.domain;

import com.hibit.kusitms26tht3hibitback.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name ="alarm")
public class Alarm extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private Users user;

    @Column(nullable = false)
    @Schema(description = "유저 닉네임", example = "false")
    private String nickname;

    @Column(nullable = false)
    @Schema(description = "알림 타입", example = "M")
    //M = 매칭 신청 왔음, R= 매칭 결과 갱신 + 평가는 나중에..
    private char category;

    @Column(nullable = false)
    @Schema(description = "게시글 인덱스", example = "1")
    private int mid;

    @Column(nullable = false, length = 200)
    @Schema(description = "내용", example = "새로운 매칭 신청이 있습니다.")
    private String content;

    @Column(nullable = false)
    @Schema(description = "읽음", example = "false")
    private char readed;

    @Column(nullable = true, length = 50)
    @Schema(description = "오픈채팅 url", example = "https://openchat~~")
    private String openchat;

    @Builder
    public Alarm(Users user, String nickname, char category, char readed, String content, int mid, String openchat) {
        this.user = user;
        this.nickname=nickname;
        this.category = category;
        this.mid = mid;
        this.content = content;
        this.openchat = openchat;
        this.readed=readed;
    }
    public void delete(){ this.readed='Y';}
}
