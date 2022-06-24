package org.zerock.onmomProject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.onmomProject.entity.FreeBoardComment;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.entity.ReviewBoard;
import org.zerock.onmomProject.entity.ReviewBoardComment;

import java.util.stream.IntStream;

@SpringBootTest
public class ReviewCommentRepositoryTests {

    @Autowired
    private ReviewCommentRepository reviewCommentRepository;

    @Test
    public void insertComment() {

        IntStream.rangeClosed(1, 300).forEach(i -> {

            ReviewBoard reviewBoard = ReviewBoard.builder()
                    .area("area....." + i)
                    .content("content....." + i)
                    .title("Title..." + i)
                    .build();

            Member member = Member.builder()
                    .member_id("u"+i)
                    .nickname("user"+i)
                    .pw("1111")
                    .build();

            ReviewBoardComment reviewBoardComment = ReviewBoardComment.builder()
                    .content("Reply......." +i)
                    .reviewBoard(reviewBoard)
                    .member(member)
                    .build();
            reviewCommentRepository.save(reviewBoardComment);
        });
    }

}
