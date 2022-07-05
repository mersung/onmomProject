package org.zerock.onmomProject.service;

import org.zerock.onmomProject.dto.MemberDTO;
import org.zerock.onmomProject.entity.Member;

public interface MemberService {

    MemberDTO selectMember(String member_id);

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
                .regDate(member.getRegDate())
                .build();
        return memberDTO;
    }

}
