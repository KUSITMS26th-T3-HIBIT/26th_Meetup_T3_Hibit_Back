package com.hibit.kusitms26tht3hibitback.domain;

import com.hibit.kusitms26tht3hibitback.BaseTimeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table(name ="matching")
public class Matching extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private Users user;

    @Column(nullable = false, length = 20)
    @Schema(description = "제목", example = "함께 관람 하실 분 구합니다!")
    private String title;

    @Column(nullable = false, length = 20)
    @Schema(description = "전시회 이름", example = "장 줄리앙 : 그러면, 거기")
    private String exhibition;

    @Column(nullable = false, length = 200)
    @Schema(description = "내용", example = "장 줄리앙의 첫 회고전이자, 저의 첫 매칭~~")
    private String content;

    @Column(nullable = false, length = 1)
    @Schema(description = "전시 카테고리", example = "1")
    private int category;

    @Column(nullable = false, length = 1)
    @Schema(description = "인원수", example = "2")
    private int number;

    @Column(nullable = false)
    @Schema(description = "시작 날짜", example = "2022-11-10")
    private LocalDate start_date;

    @Column(nullable = false)
    @Schema(description = "마감 날짜", example = "2022-11-18")
    private LocalDate finish_date;

    @Column(nullable = false)
    @Schema(description = "모집마감 여부", example = "false")
    private boolean finish;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int view;

    @Column(nullable = false, length = 50)
    @Schema(description = "오픈채팅 url", example = "https://openchat~~")
    private String openchat;

    @Column(nullable = false, length = 200)
    @Schema(description = "원하는 메이트 정보", example = "2030여성으로, 수도권에 거주하시는 분~~")
    private String want;

    @Column(nullable = false)
    @Schema(description = "게시글 삭제 여부", example = "N")
    private char deleteYn;

    @Builder
    public Matching(Users user, String title, String exhibition, String content, int category, int number,
                    LocalDate start_date,LocalDate finish_date,
                    boolean finish, int view, String openchat, String want, char deleteYn) {
        this.user = user;
        this.title = title;
        this.exhibition = exhibition;
        this.content = content;
        this.category = category;
        this.number = number;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.finish = finish;
        this.view = view;
        this.openchat = openchat;
        this.want = want;
        this.deleteYn= deleteYn;
    }

    public void update(String title, String exhibition, String content, int category, int number,
                       LocalDate start_date, LocalDate finish_date,
                       String openchat, String want) {
        this.title = title;
        this.exhibition = exhibition;
        this.content = content;
        this.category = category;
        this.number = number;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.openchat = openchat;
        this.want = want;
    }
    public void increaseView(){
        this.view++;
    }
    public void delete(){
        this.deleteYn = 'Y';
    }
}
