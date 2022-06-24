package org.zerock.onmomProject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.entity.ReviewBoard;
import org.zerock.onmomProject.entity.ReviewBoardComment;

import java.util.stream.IntStream;

@SpringBootTest
public class ReviewCommentRepositoryTests {

    @Autowired
    private ReviewCommentRepository repository;

    @Test
    public void insert(){
        IntStream.rangeClosed(1,100).forEach(i -> {
            Member member = Member.builder()
                    .member_id("u"+i)
                    .nickname("user"+i)
                    .pw("1111")
                    .build();

            ReviewBoard reviewBoard = ReviewBoard.builder()
                    .area("area....." + i)
                    .content("content....." + i)
                    .hate_cnt((long)(Math.random()*100)) // 1부터 100사이 난수 발생
                    .like_cnt((long)(Math.random()*100)) // 1부터 100사이 난수 발생
                    .title("Title..." + i)
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
