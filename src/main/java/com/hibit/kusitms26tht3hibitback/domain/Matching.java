package com.hibit.kusitms26tht3hibitback.domain;

import com.hibit.kusitms26tht3hibitback.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private String title;

    @Column(nullable = false, length = 20)
    private String exhibition;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false, length = 1)
    private int number;

    @Column(nullable = false)
    private LocalDateTime start_date;

    @Column(nullable = false)
    private LocalDateTime finish_date;

    @Column(nullable = false)
    private LocalDateTime end_date;

    @Column(nullable = false)
    private boolean end;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int view;

    @Column(nullable = false, length = 50)
    private String openchat;

    @Column(nullable = false, length = 500)
    private String want;

    @Column(nullable = true)
    private char deleteYn;

    @Builder
    public Matching(Users user, String title, String exhibition, String content, int number,
                    LocalDateTime start_date,LocalDateTime finish_date, LocalDateTime end_date,
                    boolean end, int view, String openchat, String want) {
        this.user = user;
        this.title = title;
        this.exhibition = exhibition;
        this.content = content;
        this.number = number;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.end_date = end_date;
        this.end = end;
        this.view = view;
        this.openchat = openchat;
        this.want = want;
    }
    public void update(String title, String exhibition, String content, int number,
                       LocalDateTime start_date, LocalDateTime finish_date, LocalDateTime end_date,
                       String openchat, String want) {
        this.title = title;
        this.exhibition = exhibition;
        this.content = content;
        this.number = number;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.end_date = end_date;
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
