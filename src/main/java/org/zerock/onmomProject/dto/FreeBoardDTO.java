package org.zerock.onmomProject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.onmomProject.entity.Member;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FreeBoardDTO {

    private Long free_id;

    private String member_id;

    private String nickname;

    private String title;

    private String content;

    private Long like_cnt;

    private Long hate_cnt;

    private LocalDateTime regDate;

    private LocalDateTime modDate;
}
