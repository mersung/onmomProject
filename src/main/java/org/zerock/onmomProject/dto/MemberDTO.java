package org.zerock.onmomProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.onmomProject.entity.MemberRole;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String member_id;

    private String nickname;

    private String pw;

    private MemberRole role;

    private LocalDateTime regDate;

    /////////////////////////////////////////////////////내가 쓴 글 수정중
    // N관계의 테이블을 list 형태로 선언
    private List<FreeBoardDTO> memberFreeBoardList;
    private List<ReviewBoardDTO> memberReviewBoardList;
    /////////////////////////////////////////////////////내가 쓴 글 수정중



}
