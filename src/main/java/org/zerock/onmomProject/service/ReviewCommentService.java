package org.zerock.onmomProject.service;

import org.zerock.onmomProject.dto.ReviewBoardCommentDTO;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.entity.ReviewBoard;
import org.zerock.onmomProject.entity.ReviewBoardComment;

import java.util.List;

public interface ReviewCommentService {
    // 후기 게시판 댓글 전부 호출
    List<ReviewBoardCommentDTO> getListOfReviewBoard(Long review_id);

    // 후기 게시판 댓글 추가
    Long register(ReviewBoardCommentDTO reviewBoardCommentDTO);

    // 특정 댓글 수정
    void modify(ReviewBoardCommentDTO reviewBoardCommentDTO);

    // 특정 댓글 삭제
    void remove(Long comment_id);

    default ReviewBoardComment dtoToEntity(ReviewBoardCommentDTO reviewBoardCommentDTO){
        ReviewBoardComment reviewBoardComment = ReviewBoardComment.builder()
                .comment_id(reviewBoardCommentDTO.getComment_id())
                .review_id(ReviewBoard.builder().review_id(reviewBoardCommentDTO.getReview_id()).build())
                .member_id(Member.builder().member_id(reviewBoardCommentDTO.getMember_id()).build())
                .content(reviewBoardCommentDTO.getContent())
                .build();
        return reviewBoardComment;
    }

    default ReviewBoardCommentDTO entityToDto(ReviewBoardComment reviewBoardComment){

        ReviewBoardCommentDTO reviewBoardCommentDTO = ReviewBoardCommentDTO.builder()
                .comment_id(reviewBoardComment.getComment_id())
                .review_id(reviewBoardComment.getReview_id().getReview_id())
                .member_id(reviewBoardComment.getMember_id().getMember_id())
                .nickname(reviewBoardComment.getMember_id().getNickname())
                .content(reviewBoardComment.getContent())
                .regDate(reviewBoardComment.getRegDate())
                .modDate(reviewBoardComment.getModDate())
                .build();
        return reviewBoardCommentDTO;
    }
}
