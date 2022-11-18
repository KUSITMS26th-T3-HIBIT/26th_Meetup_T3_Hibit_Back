package com.hibit.kusitms26tht3hibitback.dto;


import com.hibit.kusitms26tht3hibitback.domain.Posts;
import com.hibit.kusitms26tht3hibitback.domain.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

@NoArgsConstructor
@Getter
public class PostsSaveRequestDto {

    private Users user;
    private String title;
    private String content;
    private String file;
    private char deleteYn;


    @Builder
    public PostsSaveRequestDto(Users user, String title, String content, String file, char deleteYn) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.file = file;
        this.deleteYn = deleteYn;
    }

    public Posts toEntity(){
        return Posts.builder()
                .user(user)
                .title(title)
                .content(content)
                .file(file)
                .deleteYn('N')
                .build();
    }

    public void setUser(Users user) {
        this.user = user;
    }

}
