package org.zerock.onmomProject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.onmomProject.entity.FreeBoard;
import org.zerock.onmomProject.entity.FreeBoardComment;
import org.zerock.onmomProject.entity.Member;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class FreeBoardCommentRepositoryTests {

    @Autowired
    private FreeBoardCommentRepository freeBoardCommentRepository;

    @Test
    public void insertReply(){
        IntStream.rangeClosed(1,100).forEach(i ->{

            long free_id  = (long)(Math.random() * 100) + 1;

            FreeBoardComment freeBoardComment =FreeBoardComment.builder()
                    .content("Reply......." +i)
                    .board(FreeBoard.builder().free_id(free_id)
                            .content("content...modify....." + i )
                            .title("Title...modify..." + i)
                            .build())
                    .member(Member.builder()
                            .member_id("u@dfsafdsf"+i)
                            .nickname("user"+i)
                            .pw("1111")
                            .build())
                    .build();
            freeBoardCommentRepository.save(freeBoardComment);
        });
    }


    @Transactional
    @Test
    public void readReply1(){

        Optional<FreeBoardComment> result = freeBoardCommentRepository.findById(2L);

        FreeBoardComment freeBoardComment = result.get();


        System.out.println(freeBoardComment);
        System.out.println(freeBoardComment.getComment_id());
    }


}
