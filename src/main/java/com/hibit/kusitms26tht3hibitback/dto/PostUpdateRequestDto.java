package com.hibit.kusitms26tht3hibitback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostUpdateRequestDto {
    private String title;
    private String content;
    private String file;

    @Builder
    public PostUpdateRequestDto(String title, String content, String file){
        this.title=title;
        this.content=content;
        this.file=file;
    }
}
