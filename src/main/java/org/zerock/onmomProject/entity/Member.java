package org.zerock.onmomProject.entity;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
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

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String pw;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<MemberRole> roleSet = new HashSet<>();

    // 권한 부여 추가
    public void addMemberRole(MemberRole memberRole){
        roleSet.add(memberRole);
    }

    // 닉네임 변경
    public void modify(String nickname){
        this.nickname = nickname;
    }
}
