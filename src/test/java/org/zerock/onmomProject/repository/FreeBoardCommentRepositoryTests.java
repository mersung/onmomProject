package org.zerock.onmomProject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        IntStream.rangeClosed(1,300).forEach(i ->{


            FreeBoard freeBoard = FreeBoard.builder()
                    .content("content....." + i )
                    .title("Title..." + i)
                    .build();

            Member member = Member.builder()
                    .member_id("u"+i)
                    .nickname("user"+i)
                    .pw("1111")
                    .build();

            FreeBoardComment freeBoardComment =FreeBoardComment.builder()
                    .content("Reply......." +i)
                    .board(freeBoard)
                    .member(member)
                    .build();
            freeBoardCommentRepository.save(freeBoardComment);
        });
    }

}
