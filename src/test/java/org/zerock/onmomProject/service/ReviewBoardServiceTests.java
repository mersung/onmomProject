package org.zerock.onmomProject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.onmomProject.dto.ReviewBoardDTO;
import org.zerock.onmomProject.dto.ReviewPageRequestDTO;
import org.zerock.onmomProject.dto.ReviewPageResultDTO;
import org.zerock.onmomProject.entity.Member;

import static org.zerock.onmomProject.entity.QMember.member;

@SpringBootTest
public class ReviewBoardServiceTests {

    @Autowired
    private ReviewBoardService reviewBoardService;

    // 게시물 등록 테스트
    @Test
    public void testReviewRegister() {

        ReviewBoardDTO dto = ReviewBoardDTO.builder()
                .area("영등포구")
                .content("내용수정........")
                .hate_cnt((long)(Math.random()*100)) // 1부터 100사이 난수 발생
                .like_cnt((long)(Math.random()*100)) // 1부터 100사이 난수 발생
                .title("제목수정........")
                .member_id("u99")// 현재 데이터베이스에 존재하는 회원 이메일
                .build();

        Long review_id = reviewBoardService.register(dto);
    }

    // 게시물 목록 처리 테스트
    @Test
    public void testReviewList() {
        ReviewPageRequestDTO reviewPageRequestDTO = new ReviewPageRequestDTO();

        ReviewPageResultDTO<ReviewBoardDTO, Object[]> result = reviewBoardService.getList(reviewPageRequestDTO);

        for (ReviewBoardDTO reviewBoardDTO : result.getDtoList()) {
            System.out.println(reviewBoardDTO);
        }
    }

    // 게시물 삭제 처리 테스트
    @Test
    public void testRemove(){
        Long review_id = 10L;
        reviewBoardService.removeWithReplies(review_id);
    }
}
