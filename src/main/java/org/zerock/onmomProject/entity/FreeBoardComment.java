package org.zerock.onmomProject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"member", "board"})
public class FreeBoardComment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private FreeBoard board;

    @Column(length = 1000, nullable = false)
    private String content;

    public void changeContent(String content){
        this.content = content;
    }

}
