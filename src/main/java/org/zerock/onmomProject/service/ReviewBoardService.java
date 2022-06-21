package org.zerock.onmomProject.service;

import org.zerock.onmomProject.dto.ReviewBoardCommentDTO;
import org.zerock.onmomProject.dto.ReviewBoardDTO;
import org.zerock.onmomProject.dto.ReviewPageRequestDTO;
import org.zerock.onmomProject.dto.ReviewPageResultDTO;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.entity.ReviewBoard;
import org.zerock.onmomProject.entity.ReviewBoardComment;

import java.time.LocalDateTime;
import java.util.List;

public interface ReviewBoardService {
    // 후기 게시판 글추가
    Long register(ReviewBoardDTO reviewBoardDTO);

    // 목록 처리
    ReviewPageResultDTO<ReviewBoardDTO, Object[]> getList(ReviewPageRequestDTO reviewPageRequestDTO);

    ReviewBoardDTO get(Long review_id);

    // 특정 글 삭제
    void removeWithReplies(Long review_id);

    // 특정 글 수정
    void modify(ReviewBoardCommentDTO reviewBoardCommentDTO);



    default ReviewBoard dtoToEntity(ReviewBoardDTO reviewBoardDTO) {
        ReviewBoard reviewBoard = ReviewBoard.builder()
                .review_id(reviewBoardDTO.getReview_id())
                .member(Member.builder().member_id(reviewBoardDTO.getMember_id()).build())
                .title(reviewBoardDTO.getTitle())
                .content(reviewBoardDTO.getContent())
                .like_cnt(reviewBoardDTO.getLike_cnt())
                .hate_cnt(reviewBoardDTO.getHate_cnt())
                .area(reviewBoardDTO.getArea())
                .img(reviewBoardDTO.getImg())
                .build();
        return reviewBoard;
    }



    default ReviewBoardDTO entityToDto(ReviewBoard reviewBoard){

        ReviewBoardDTO reviewBoardDTO = ReviewBoardDTO.builder()
                .review_id(reviewBoard.getReview_id())
                .member_id(reviewBoard.getMember().getMember_id())
                .title(reviewBoard.getTitle())
                .content(reviewBoard.getContent())
                .like_cnt(reviewBoard.getLike_cnt())
                .hate_cnt(reviewBoard.getHate_cnt())
                .area(reviewBoard.getArea())
                .img(reviewBoard.getImg())
                .regDate(reviewBoard.getRegDate())
                .modDate(reviewBoard.getModDate())
                .build();
        return reviewBoardDTO;
    }


}
