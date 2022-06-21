package org.zerock.onmomProject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.onmomProject.dto.ReviewBoardCommentDTO;
import org.zerock.onmomProject.entity.ReviewBoard;
import org.zerock.onmomProject.entity.ReviewBoardComment;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReviewCommentServiceImpl implements ReviewCommentService{

    private final ReviewCommentRepository reviewCommentRepository;

    // 후기 게시판 호출
    @Override
    public List<ReviewBoardCommentDTO> getListOfReviewBoard(Long review_id) {
        ReviewBoard reviewBoard = ReviewBoard.builder().review_id(review_id).build();

        List<ReviewBoardComment> result = reviewCommentRepository.findByReviewBoard(review_id);

        return result.stream().map(reviewBoardComment -> entityToDto(reviewBoardComment)).collect(Collectors.toList());
    }

    // 댓글 등록
    @Override
    public Long register(ReviewBoardCommentDTO reviewBoardCommentDTO) {
        ReviewBoardComment reviewBoardComment = dtoToEntity(reviewBoardCommentDTO);

        reviewCommentRepository.save(reviewBoardComment);

        return reviewBoardComment.getComment_id();
    }

    // 댓글 수정
    @Override
    public void modify(ReviewBoardCommentDTO reviewBoardCommentDTO) {
        Optional<ReviewBoardComment> result = reviewCommentRepository.findById(reviewBoardCommentDTO.getComment_id());

        if(result.isPresent()){ // optional 보유 값 null 확인
            ReviewBoardComment reviewBoardComment = result.get();
            reviewBoardComment.changeContent(reviewBoardCommentDTO.getContent());

            reviewCommentRepository.save(reviewBoardComment);
        }
    }

    // 댓글 삭제
    @Override
    public void remove(Long comment_id) {
        reviewCommentRepository.deleteById(comment_id);

    }
}
