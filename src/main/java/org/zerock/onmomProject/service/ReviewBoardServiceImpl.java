package org.zerock.onmomProject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.onmomProject.dto.ReviewBoardDTO;
import org.zerock.onmomProject.dto.ReviewPageRequestDTO;
import org.zerock.onmomProject.dto.ReviewPageResultDTO;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.entity.ReviewBoard;
import org.zerock.onmomProject.repository.ReviewBoardRepository;
import org.zerock.onmomProject.repository.ReviewCommentRepository;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewBoardServiceImpl implements ReviewBoardService {

    private final ReviewBoardRepository reviewBoardRepository; //자동 주입 final

    private final ReviewCommentRepository reviewCommentRepository;

    @Override
    public Long register(ReviewBoardDTO reviewBoardDTO) {
        log.info(reviewBoardDTO);

        ReviewBoard reviewBoard = dtoToEntity(reviewBoardDTO);

        reviewBoardRepository.save(reviewBoard);

        return reviewBoard.getReview_id();
    }

    // 페이징처리
    @Override
    public ReviewPageResultDTO<ReviewBoardDTO, Object[]> getList(ReviewPageRequestDTO reviewPageRequestDTO) {
        log.info(reviewPageRequestDTO);

        Function<ReviewBoard, ReviewBoardDTO> fn = (en -> entityToDTO((ReviewBoard) en[0],(Member)en[1],(Long)en[2]));

        Page<Object[]> result = reviewBoardRepository.searchPage(
                reviewPageRequestDTO.getArea(),
                reviewPageRequestDTO.getType(),
                reviewPageRequestDTO.getKeyword(),
                reviewPageRequestDTO.getPageable(Sort.by("review_id").descending()));

        return new ReviewPageResultDTO<>(result, fn);
    }

    @Override
    public ReviewBoardDTO get(Long review_id) {
        Object result = reviewBoardRepository.getReviewBoardByReview_id(review_id);

        Object[] arr = (Object[])result;

        return entityToDTO((ReviewBoard)arr[0], (Member)arr[1], (Long)arr[2]);
    }


    @Transactional
    @Override
    public void removeWithReplies(Long review_id) { //삭제 기능 구현, 트렌젝션 추가
        //댓글부터 삭제
        reviewCommentRepository.deleteById(review_id);

        reviewBoardRepository.deleteById(review_id);
    }

    @Transactional
    @Override
    public void modify(ReviewBoardDTO reviewBoardDTO) {
        ReviewBoard reviewBoard = reviewBoardRepository.getOne(reviewBoardDTO.getReview_id());

            reviewBoard.changeTitle(reviewBoardDTO.getTitle());
            reviewBoard.changeContent(reviewBoardDTO.getContent());

            reviewBoardRepository.save(reviewBoard);

    }
}
