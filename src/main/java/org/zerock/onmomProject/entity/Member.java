package org.zerock.onmomProject.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Member extends BaseEntity {

    @Id
    @Column(length = 50)
    private String member_id;

    @Column(nullable = false, length = 50)
    private String nickname;

    @Column(nullable = false, length = 50)
    private String pw;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<MemberRole> roleSet;


}
