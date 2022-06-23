package org.zerock.onmomProject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.onmomProject.entity.Member;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            Member member = Member.builder()
                    .member_id("u"+i)
                    .nickname("user"+i)
                    .pw("1111")
                    .build();

            memberRepository.save(member);
        });
    }
}
