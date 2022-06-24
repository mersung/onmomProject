package org.zerock.onmomProject.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.onmomProject.entity.FreeBoard;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.entity.ReviewBoard;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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

    @Transactional
    @Test
    public void testRead1() { // 조회

        Optional<ReviewBoard> result = reviewBoardRepository.findById(100L); // 데이터베이스에 존재하는 번호

        ReviewBoard reviewBoard = result.get();

        System.out.println(reviewBoard);
        System.out.println(reviewBoard.getMember());

    }

    // 여기서부터 ReviewBoardRepository.java 테스트코드
    @Transactional
    @Test
    public void testReadWithMember() {
        Object result = reviewBoardRepository.getReviewBoardWithMember(100L);

        Object[] arr = (Object[])result;

        System.out.println("-----------------------------------");
        System.out.println(Arrays.toString(arr));
    }

    @Transactional
    @Test
    public void testGetReviewBoardWithReviewBoardComment() {
        List<Object[]> result = reviewBoardRepository.getReviewBoardWithReviewBoardComment(100L);
        for(Object[] arr : result) {
            System.out.println(Arrays.toString(arr));
        }
    }

    @Transactional
    @Test
    public void testWithReviewBoardCommentCount() {

        Pageable pageable = PageRequest.of(0,10, Sort.by("review_id").descending());

        Page<Object[]> result = reviewBoardRepository.getReviewBoardWithReviewBoardCommentCount(pageable);

        result.get().forEach(row -> {

            Object[] arr = (Object[])row;

            System.out.println(Arrays.toString(arr));
        });
    }

    @Transactional
    @Test
    public void testRead3() {

        Object result = reviewBoardRepository.getReviewBoardByReview_id(100L);

        Object[] arr = (Object[])result;

        System.out.println(Arrays.toString(arr));
    }
}
