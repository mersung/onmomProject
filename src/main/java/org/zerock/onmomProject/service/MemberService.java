package org.zerock.onmomProject.service;

import org.zerock.onmomProject.dto.MemberDTO;
import org.zerock.onmomProject.entity.Member;

public interface MemberService {

    MemberDTO selectMember(String member_id);

//    MemberDTO save(MemberDTO memberDTO) throws IOException;
//    List<MemberDTO>  getListOfMember(String member_id);
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
                .regDate(member.getRegDate())
                .build();
        return memberDTO;
    }


}
