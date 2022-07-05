package org.zerock.onmomProject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.onmomProject.dto.MemberDTO;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.service.MemberService;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;


    @Test
    public void insertMembers() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            Member member = Member.builder()
                    .member_id("u@dfsafdsf"+i)
                    .nickname("user"+i)
                    .pw("1111")
                    .build();

            memberRepository.save(member);
        });
    }

    @Test
    public void get(){
        String member_id = "nsj1427@gmail.com";

        MemberDTO memberDTO = memberService.selectMember(member_id);

        System.out.println(memberDTO);
    }


    @Test
    public void testSelect(){
        String member_id="nsj1427@gmail.com";

        Optional<Member> result = memberRepository.findByEmail(member_id);

        System.out.println("================");

        if (result.isPresent()){
            Member member =result.get();
            System.out.println(member);
        }
    }

    @Test
    public void testUpdate(){


        Member member = Member.builder().member_id("nsj1427@gmail.com").nickname("nazzu").pw("1111").build();

        System.out.println(memberRepository.save(member));






    }
}
