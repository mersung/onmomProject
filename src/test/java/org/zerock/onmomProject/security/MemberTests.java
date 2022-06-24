package org.zerock.onmomProject.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.entity.MemberRole;
import org.zerock.onmomProject.repository.MemberRepository;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberTests {

    @Autowired
    private MemberRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies(){
        // 1 - 10 GUEST
        // 11 = 20 USER

        IntStream.rangeClosed(1,20).forEach(i -> {
            Member member= Member.builder()
                    .member_id("user" + i + "@onmom.com")
                    .nickname("user"+i)
                    .pw(passwordEncoder.encode("1111"))
                    .build();

            //default role
            member.addMemberRole(MemberRole.GUEST);

            if (i > 10){
                member.addMemberRole(MemberRole.USER);
            }

            repository.save(member);
        });
    }
}
