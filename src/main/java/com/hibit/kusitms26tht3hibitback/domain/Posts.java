package com.hibit.kusitms26tht3hibitback.domain;

import com.hibit.kusitms26tht3hibitback.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Posts extends BaseTimeEntity {
    //커뮤 카테고리 추가 필요
    //댓글 추가 필요
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_idx")
    private Users user;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 200, nullable = false)
    private String content;

    @Column(length = 50, nullable = true)
    private String file;

    @Column(nullable = false, columnDefinition = "integer default 0")
    private int view;

    @Column(nullable = false)
    private char deleteYn;

    @Builder
    public Posts(String title, String content, String file, int view, char deleteYn){
        this.title = title;
        this.content = content;
        this.file = file;
        this.view = view;
        this.deleteYn = deleteYn;
    }

    public void update(String title, String content,String file){
        this.title=title;
        this.content = content;
        this.file=file;
    }

    public void increaseView(){
        this.view++;
    }
    public void delete(){
        this.deleteYn = 'Y';
    }
}
