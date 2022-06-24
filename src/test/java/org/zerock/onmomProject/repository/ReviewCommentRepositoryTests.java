package org.zerock.onmomProject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.entity.ReviewBoard;
import org.zerock.onmomProject.entity.ReviewBoardComment;

import java.util.stream.IntStream;

@SpringBootTest
public class ReviewCommentRepositoryTests {

    @Autowired
    private ReviewCommentRepository repository;

    @Transactional
    @Test
    public void insert(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            Member member = Member.builder()
                    .member_id("u"+i)
                    .build();

            ReviewBoard reviewBoard = ReviewBoard.builder()
                    .member(member)
                    .build();

            ReviewBoardComment reviewBoardComment = ReviewBoardComment.builder()
                    .member(member)
                    .reviewBoard(reviewBoard)
                    .content("content..."+i)
                    .build();

            repository.save(reviewBoardComment);

        });

    }

}
