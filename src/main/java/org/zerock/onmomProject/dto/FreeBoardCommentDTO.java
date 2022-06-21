package org.zerock.onmomProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.onmomProject.entity.FreeBoard;
import org.zerock.onmomProject.entity.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoardCommentDTO {

    private Long comment_id;

    private Member member_id;

    private FreeBoard board_id;

    private String content;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}