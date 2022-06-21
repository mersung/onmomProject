package org.zerock.onmomProject.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Builder
@AllArgsConstructor
@Data
public class FreePageRequestDTO {
    //자유 게시판용

    private int page;
    private int size;
    private String type; // 제목/내용/제목+내용 선택
    private String keyword; //검색 단어

    public FreePageRequestDTO(){
        this.page = 1;
        this.size = 10;
    }

    public Pageable getPageable(Sort sort){
        return PageRequest.of(page-1, size, sort);
    }
}
