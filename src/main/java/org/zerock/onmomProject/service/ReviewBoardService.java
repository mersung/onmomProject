package org.zerock.onmomProject.service;

import org.zerock.onmomProject.dto.*;
import org.zerock.onmomProject.entity.Image;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.entity.ReviewBoard;
import org.zerock.onmomProject.entity.ReviewBoardComment;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface ReviewBoardService {
    // 후기 게시판 글추가
    Long register(ReviewBoardDTO reviewBoardDTO);

    // 목록 처리
    ReviewPageResultDTO<ReviewBoardDTO, Object[]> getList(ReviewPageRequestDTO reviewPageRequestDTO);

    ReviewBoardDTO get(Long review_id);

    // 특정 글 삭제
    void removeWithReplies(Long review_id);

    // 특정 글 수정
    void modify(ReviewBoardDTO reviewBoardDTO);




    default Map<String, Object> dtoToEntity(ReviewBoardDTO reviewBoardDTO) {

        Map<String, Object> entityMap = new HashMap<>();

        ReviewBoard reviewBoard = ReviewBoard.builder()
                .review_id(reviewBoardDTO.getReview_id())
                .member(Member.builder().member_id(reviewBoardDTO.getMember_id()).build())
                .title(reviewBoardDTO.getTitle())
                .content(reviewBoardDTO.getContent())
                .like_cnt(reviewBoardDTO.getLike_cnt())
                .hate_cnt(reviewBoardDTO.getHate_cnt())
                .area(reviewBoardDTO.getArea())
                .build();
        entityMap.put("reviewBoard", reviewBoard);

        List<ImageDTO> imageDTOList = reviewBoardDTO.getImageDTOList();

        if(imageDTOList != null && imageDTOList.size() > 0 ){
            List<Image> imageList = imageDTOList.stream().map(imageDTO -> {

                Image image = Image.builder()
                        .path(imageDTO.getPath())
                        .imgName(imageDTO.getImgName())
                        .uuid(imageDTO.getUuid())
                        .review(reviewBoard)
                        .build();

                return image;
            }).collect(Collectors.toList());
            entityMap.put("imgList", imageList);
        }

        return entityMap;
    }



//    default ReviewBoardDTO entityToDto(ReviewBoard reviewBoard, Member member, Long like_cnt){
//
//        ReviewBoardDTO reviewBoardDTO = ReviewBoardDTO.builder()
//                .review_id(reviewBoard.getReview_id())
//                .member_id(member.getMember_id())
//                .title(reviewBoard.getTitle())
//                .content(reviewBoard.getContent())
//                .like_cnt(like_cnt.longValue())
//                .hate_cnt(reviewBoard.getHate_cnt())
//                .area(reviewBoard.getArea())
//                .regDate(reviewBoard.getRegDate())
//                .modDate(reviewBoard.getModDate())
//                .build();
//        return reviewBoardDTO;
//    }

    // 이미지가 여러개일 경우 이거 사용, 테이블이 하나 더 생겼으므로
    default ReviewBoardDTO entitiesToDTO(ReviewBoard reviewBoard, List<Image> images){
        ReviewBoardDTO reviewBoardDTO = ReviewBoardDTO.builder()
                .review_id(reviewBoard.getReview_id())
                .member_id(reviewBoard.getMember().getNickname())
                .title(reviewBoard.getTitle())
                .content(reviewBoard.getContent())
                .like_cnt(reviewBoard.getLike_cnt())
                .hate_cnt(reviewBoard.getHate_cnt())
                .area(reviewBoard.getArea())
                .regDate(reviewBoard.getRegDate())
                .modDate(reviewBoard.getModDate())
                .build();

        List<ImageDTO> imageDTOList = images.stream().map(image -> {
            return ImageDTO.builder()
                    .imgName(image.getImgName())
                    .path(image.getPath())
                    .uuid(image.getUuid())
                    .build();
        }).collect(Collectors.toList());

        reviewBoardDTO.setImageDTOList(imageDTOList);
        return reviewBoardDTO;
    }




}
