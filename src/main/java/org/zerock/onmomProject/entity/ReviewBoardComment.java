package org.zerock.onmomProject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"member", "reviewBoard"})
public class ReviewBoardComment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private ReviewBoard reviewBoard;

    @Column(nullable = false, length = 1000)
    private String content;

    public void changeContent(String content){
        this.content = content;
    }
}
