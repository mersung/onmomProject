package org.zerock.onmomProject.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = {"member_id", "board_id"})
public class FreeBoardComment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment;

    @ManyToOne
    private Member member_id;

    @ManyToOne
    private FreeBoard board_id;

    private String content;

}
