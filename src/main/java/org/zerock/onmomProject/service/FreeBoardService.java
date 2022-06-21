package org.zerock.onmomProject.service;

import org.zerock.onmomProject.dto.FreeBoardDTO;
import org.zerock.onmomProject.entity.FreeBoard;
import org.zerock.onmomProject.entity.Member;

public interface FreeBoardService {
    Long register(FreeBoardDTO dto);

    default FreeBoard dtoToEntity(FreeBoardDTO dto){
        Member member = Member.builder().member_id(dto.getMember_id()).build();

        FreeBoard freeBoard = FreeBoard.builder()
                .free_id(dto.getFree_id())
                .title(dto.getTitle())
                .content(dto.getContent())
                .member_id(member)
                .build();
        return freeBoard;
    }
    default FreeBoardDTO entityToDTO(FreeBoard freeBoard, Member member, Long replyCount) {

        FreeBoardDTO freeBoardDTO = FreeBoardDTO.builder()
                .free_id(freeBoard.getFree_id())
                .title(freeBoard.getTitle())
                .content(freeBoard.getContent())
                .regDate(freeBoard.getRegDate())
                .modDate(freeBoard.getModDate())
                .member_id(member.getMember_id())
                .like_cnt(freeBoard.getLike_cnt()) //int로 처리하도록
                .hate_cnt(freeBoard.getHate_cnt())
                .build();

        return freeBoardDTO;

    }
}
