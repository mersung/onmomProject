package org.zerock.onmomProject.service;

import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.zerock.onmomProject.dto.FreeBoardDTO;
import org.zerock.onmomProject.dto.FreePageRequestDTO;
import org.zerock.onmomProject.dto.FreePageResultDTO;
import org.zerock.onmomProject.entity.FreeBoard;
import org.zerock.onmomProject.entity.Member;

public interface FreeBoardService {
    Long register(FreeBoardDTO dto);

    FreePageResultDTO<FreeBoardDTO, Object[]> getList (FreePageRequestDTO freePageRequestDTO);

    FreeBoardDTO get(Long free_id);

    void removeWithReplies(Long free_id);

    void modify(FreeBoardDTO freeBoardDTO);
    default FreeBoard dtoToEntity(FreeBoardDTO dto){

        Member member = Member.builder().member_id(dto.getMember_id()).build();

        FreeBoard freeBoard = FreeBoard.builder()
                .free_id(dto.getFree_id())
                .title(dto.getTitle())
                .content(dto.getContent())
                .like_cnt(dto.getLike_cnt())
                .hate_cnt(dto.getHate_cnt())
                .member(member)
                .build();
        return freeBoard;
    }
    default FreeBoardDTO entityToDTO(FreeBoard freeBoard, Member member,Long replyCount) {

        FreeBoardDTO freeBoardDTO = FreeBoardDTO.builder()
                .free_id(freeBoard.getFree_id())
                .member_id(member.getMember_id())
                .title(freeBoard.getTitle())
                .content(freeBoard.getContent())
                .replyCount(replyCount.intValue())
                .like_cnt(freeBoard.getLike_cnt())
                .hate_cnt(freeBoard.getHate_cnt())
                .regDate(freeBoard.getRegDate())
                .modDate(freeBoard.getModDate())
                .build();

        return freeBoardDTO;

    }
}
