package org.zerock.onmomProject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.onmomProject.entity.FreeBoard;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.entity.ReviewBoard;

import java.util.stream.IntStream;

@SpringBootTest
public class ReviewBoardRepositoryTests {

    @Autowired
    private ReviewBoardRepository reviewBoardRepository;

    @Test
    public void insertReviewBoard() {

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

            reviewBoardRepository.save(reviewBoard);

        });
    }
}
