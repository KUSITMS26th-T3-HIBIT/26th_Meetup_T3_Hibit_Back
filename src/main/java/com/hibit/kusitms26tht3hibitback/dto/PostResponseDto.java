package com.hibit.kusitms26tht3hibitback.dto;

import com.hibit.kusitms26tht3hibitback.domain.Posts;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PostResponseDto {

    private int idx;
    private int user;
    private String title;
    private String content;
    private String file;
    private int view;
    private char deleteYn;

    public PostResponseDto(@NotNull Posts entity){
        this.idx= entity.getIdx();
        this.user=entity.getUser().getIdx();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.file=entity.getFile();
        this.view=entity.getView();
        this.deleteYn=entity.getDeleteYn();
    }
}
