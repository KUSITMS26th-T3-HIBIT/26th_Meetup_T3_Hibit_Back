package com.hibit.kusitms26tht3hibitback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
public class HibitInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(nullable = false, length = 50)
    @Schema(description = "전시회 이름", example = "장 줄리앙 : 그러면, 거기")
    private String exhibition;

    @Column(nullable = false, length = 20)
    @Schema(description = "지역", example = "서울강남구")
    private String area;

    @Column(nullable = false)
    @Schema(description = "시작 날짜", example = "20221110")
    private String start_date;

    @Column(nullable = false)
    @Schema(description = "마감 날짜", example = "20221118")
    private String finish_date;

}
