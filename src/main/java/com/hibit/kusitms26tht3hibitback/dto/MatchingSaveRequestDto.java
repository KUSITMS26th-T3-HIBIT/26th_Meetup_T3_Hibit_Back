package com.hibit.kusitms26tht3hibitback.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hibit.kusitms26tht3hibitback.domain.Matching;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@NoArgsConstructor
@Getter
public class MatchingSaveRequestDto {
    private Users user;
    private String title;
    private String exhibition;
    private String content;
    private int number;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate start_date;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate finish_date;
    private boolean finish;
    private String openchat;
    private String want;
    private char deleteYn;

    @Builder
    public MatchingSaveRequestDto(Users user, String title, String exhibition, String content,
                                  int number, LocalDate start_date, LocalDate finish_date,
                                  boolean finish, String openchat, String want, char deleteYn){
        this.user = user;
        this.title=title;
        this.exhibition=exhibition;
        this.content=content;
        this.number=number;
        this.start_date=start_date;
        this.finish_date=finish_date;
        this.finish=finish;
        this.openchat=openchat;
        this.want=want;
        this.deleteYn = deleteYn;
    }

    public Matching toEntity(){
        return Matching.builder()
                .title(title)
                .exhibition(exhibition)
                .content(content)
                .number(number)
                .start_date(start_date)
                .finish_date(finish_date)
                .finish(finish)
                .openchat(openchat)
                .want(want)
                .deleteYn(deleteYn)
                .build();
    }
    public void setUser(Users user) {
        this.user = user;
    }
}
