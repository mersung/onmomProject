package org.zerock.onmomProject.service;

import org.zerock.onmomProject.dto.MemberDTO;
import org.zerock.onmomProject.entity.Member;

import java.io.IOException;

public interface MemberService {
    MemberDTO save(MemberDTO memberDTO) throws IOException;
    MemberDTO selectMember(String member_id);
    // 수정
//    void modify(MemberDTO memberDTO);

    default Member dtoToEntity(MemberDTO dto){
        Member member = Member.builder()
                .member_id(dto.getMember_id())
                .nickname(dto.getNickname())
                .build();
        return member;
    }

    default MemberDTO entityToDto(Member member){
        MemberDTO memberDTO = MemberDTO.builder()
                .member_id(member.getMember_id())
                .nickname(member.getNickname())
                .build();
        return memberDTO;
    }


}
