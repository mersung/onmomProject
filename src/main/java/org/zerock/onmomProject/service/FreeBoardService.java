package org.zerock.onmomProject.service;

import org.zerock.onmomProject.dto.FreeBoardDTO;
import org.zerock.onmomProject.entity.FreeBoard;
import org.zerock.onmomProject.entity.Member;

public interface FreeBoardService {
    Long register(FreeBoardDTO dto);

    default FreeBoard dtoToEntity(FreeBoardDTO dto){
        Member member = Member.builder().member_id(dto.getWriterMember_id()).build();

        FreeBoard board = FreeBoard.builder()
                .free_id(dto.getFree_id())
                .title(dto.getTitle())
                .content(dto.getContent())
                .member_id(member)
                .build();
        return board;
    }
}
