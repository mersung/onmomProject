package org.zerock.onmomProject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"member_id","board_id"})
public class ReviewBoardComment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @ManyToOne
    private Member member_id;

    @ManyToOne
    private ReviewBoard board_id;

    private String content;
}
