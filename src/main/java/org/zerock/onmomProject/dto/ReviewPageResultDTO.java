package org.zerock.onmomProject.dto;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Log4j2
public class ReviewPageResultDTO<DTO, EN> {
    //리뷰 게시판 페이지는 슬라이더이므로 한 번에 불러온다. size와 페이지를 정해주지 않음


    //DTO 리스트
    private List<DTO> dtoList;

    //총 페이지 번호
    private int totalPage;

    //현재 페이지 번호
    private int page;

    //목록 사이즈
    private int size;

    //시작 페이지, 끝 페이지
    private int start, end;

    private boolean prev, next;

    //페이지 번호 목록
    private List<Integer> pageList;

    // entity형태로 받아 List형태로 정리, Function을 통해 DTO로 만들어서 결과 반환
    public ReviewPageResultDTO(Page<EN> result, Function<EN, DTO> fn){

        dtoList = result.stream().map(fn).collect(Collectors.toList());
        log.info("result, fn : "+result, fn, dtoList);
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
        log.info("dtoList!~" + dtoList);
    }

    private void makePageList(Pageable pageable){
        this.page = pageable.getPageNumber() + 1; // 0부터 시작하므로 1을 추가
        this.size = pageable.getPageSize();

        //temp end page
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;

        start = tempEnd - 9;

        prev = start > 1;

        end = totalPage > tempEnd ? tempEnd: totalPage;

        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

    }
}
