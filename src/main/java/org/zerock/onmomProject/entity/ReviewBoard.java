package org.zerock.onmomProject.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString(exclude = "member")
public class ReviewBoard extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long review_id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @Column(length = 20, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String content;

    @Column(length = 20)
    private Long like_cnt;

    @Column(length = 20)
    private Long hate_cnt;

    @Column(length = 50, nullable = false)
    private String area;

    // 리뷰 게시판 수정
    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content){
        this.content = content;
    }

    // 좋아요 싫어요 관련 DB 설정
    @PrePersist
    public void prePersist(){
        this.like_cnt = this.like_cnt == null? 0:this.like_cnt;
        this.hate_cnt = this.hate_cnt == null? 0:this.hate_cnt;
    }
}
