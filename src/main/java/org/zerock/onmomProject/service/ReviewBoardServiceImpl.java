package org.zerock.onmomProject.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.onmomProject.dto.ReviewBoardDTO;
import org.zerock.onmomProject.dto.ReviewPageRequestDTO;
import org.zerock.onmomProject.dto.ReviewPageResultDTO;
import org.zerock.onmomProject.entity.Image;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.entity.ReviewBoard;
import org.zerock.onmomProject.entity.ReviewBoardComment;
import org.zerock.onmomProject.repository.ImageRepository;
import org.zerock.onmomProject.repository.ReviewBoardRepository;
import org.zerock.onmomProject.repository.ReviewCommentRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewBoardServiceImpl implements ReviewBoardService {

    private final ReviewBoardRepository reviewBoardRepository; //자동 주입 final

    private final ReviewCommentRepository reviewCommentRepository;

    private final ImageRepository imageRepository;

    @Transactional
    @Override
    public Long register(ReviewBoardDTO reviewBoardDTO) {

        log.info("Review : "+reviewBoardDTO);
        Map<String, Object> entityMap = dtoToEntity(reviewBoardDTO);
        ReviewBoard reviewBoard = (ReviewBoard) entityMap.get("reviewBoard");
        List<Image> imageList =  (List<Image>) entityMap.get("imgList");

        reviewBoardRepository.save(reviewBoard);

        imageList.forEach(image -> {
            imageRepository.save(image);
        });
        return reviewBoard.getReview_id();
    }

    // 페이징처리
    @Override
    public ReviewPageResultDTO<ReviewBoardDTO, Object[]> getList(ReviewPageRequestDTO reviewPageRequestDTO) {
        log.info(reviewPageRequestDTO);

        Pageable pageable = reviewPageRequestDTO.getPageable(Sort.by("like_cnt").descending());
//        Page<Object[]> result = reviewBoardRepository.getListPage(pageable);
        log.info("====================");

//        result.getContent().forEach(arr -> {
//            log.info(Arrays.toString(arr));
//        });

        Function<Object[], ReviewBoardDTO> fn = (arr -> entitiesToDTO(
                (ReviewBoard) arr[0],
                (List<Image>)(Arrays.asList((Image)arr[1]))
                )
        );

//        log.info("Result~!" + new ReviewPageResultDTO<>(result, fn));
//        return new ReviewPageResultDTO<>(result, fn);

        Page<Object[]> result = reviewBoardRepository.searchPage(
                reviewPageRequestDTO.getArea(),
                reviewPageRequestDTO.getType(),
                reviewPageRequestDTO.getKeyword(),
                reviewPageRequestDTO.getPageable(Sort.by("like_cnt").descending()));

        return new ReviewPageResultDTO<>(result, fn);
    }

    @Override
    public ReviewBoardDTO get(Long review_id) {
        List<Object[]> result = reviewBoardRepository.getReviewBoardWithAll(review_id);

        ReviewBoard reviewBoard = (ReviewBoard) result.get(0)[0];

        List<Image> imageList = new ArrayList<>();

        result.forEach(arr -> {
            Image image = (Image)arr[1];
            imageList.add(image);
        });
        return entitiesToDTO(reviewBoard, imageList);
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
