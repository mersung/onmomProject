package org.zerock.onmomProject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class FreeBoardComment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment;

    @ManyToOne
    @Column(length = 50, nullable = false)
    private Member member_id;

    @ManyToOne
    @Column(length = 20, nullable = false)
    private FreeBoard board_id;

    @Column(length = 1000, nullable = false)
    private String content;

    public void changeContent(String content){
        this.content = content;
    }

}
