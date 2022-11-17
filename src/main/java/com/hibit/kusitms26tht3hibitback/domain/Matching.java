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
    private String title;

    @Column(nullable = false, length = 20)
    private String exhibition;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false, length = 1)
    private int number;

    @Column(nullable = false)
    private LocalDate start_date;

    @Column(nullable = false)
    private LocalDate finish_date;

    @Column(nullable = false)
    private boolean finish;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int view;

    @Column(nullable = false, length = 50)
    private String openchat;

    @Column(nullable = false, length = 500)
    private String want;

    @Column(nullable = false)
    @ColumnDefault("N")
    private char deleteYn;

    @Builder
    public Matching(Users user, String title, String exhibition, String content, int number,
                    LocalDate start_date,LocalDate finish_date,
                    boolean finish, int view, String openchat, String want, char deleteYn) {
        this.user = user;
        this.title = title;
        this.exhibition = exhibition;
        this.content = content;
        this.number = number;
        this.start_date = start_date;
        this.finish_date = finish_date;
        this.finish = finish;
        this.view = view;
        this.openchat = openchat;
        this.want = want;
        this.deleteYn= deleteYn;
    }

    public void update(String title, String exhibition, String content, int number,
                       LocalDate start_date, LocalDate finish_date,
                       String openchat, String want) {
        this.title = title;
        this.exhibition = exhibition;
        this.content = content;
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
