package org.zerock.onmomProject.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "member")
public class FreeBoard extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long free_id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Member member;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String content;

    @Column(length = 20)
    private Long like_cnt;

    @Column(length = 20)
    private Long hate_cnt;

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeContent(String content){
        this.content = content;
    }
}
