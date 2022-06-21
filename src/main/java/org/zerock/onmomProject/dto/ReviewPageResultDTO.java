package org.zerock.onmomProject.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReviewPageResultDTO<DTO, EN> {
    //리뷰 게시판 페이지는 슬라이더이므로 한 번에 불러온다. size와 페이지를 정해주지 않음


    //DTO 리스트
    private List<DTO> dtoList;

    // entity형태로 받아 List형태로 정리, Function을 통해 DTO로 만들어서 결과 반환
    public ReviewPageResultDTO(List<EN> result, Function<EN, DTO> fn){
        dtoList = result.stream().map(fn).collect(Collectors.toList());

    }
}
