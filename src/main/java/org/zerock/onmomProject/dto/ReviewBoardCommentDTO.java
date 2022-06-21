package org.zerock.onmomProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.entity.ReviewBoard;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewBoardCommentDTO {

    private Long comment_id;

    private Member member_id;

    private ReviewBoard review_id;

    private String content;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
