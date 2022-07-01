package org.zerock.onmomProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.onmomProject.dto.MemberDTO;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.repository.MemberRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberDTO selectMember(String member_id) {
        Optional<Member> member = memberRepository.findByEmail(member_id);
        return member.isPresent() ? entityToDto(member.get()) : null;
    }

//    @Override
//    public MemberDTO save(MemberDTO memberDTO) throws IOException {
//        String member_id = memberDTO.getMember_id();
//        memberDTO.setMember_id(member_id);
//
//        Member member = dtoToEntity(memberDTO);
//        Member validUser = memberRepository.save(member);
//        MemberDTO dto = entityToDto(validUser);
//        return dto;
//    }

//    @Override
//    public List<MemberDTO> getListOfMember(String member_id) {
//        Member member = Member.builder().member_id(member_id).build();
//
//        Optional<Member> result = memberRepository.findByEmail(member_id);
//
//        return result.stream().map(member1 -> entityToDto(member)).collect(Collectors.toList());
//    }

    // 수정
//    @Override
//    public void modify(MemberDTO memberDTO) {
//
//        Optional<Member> result = memberRepository.findByEmail(memberDTO.getMember_id());
//
//        if (result.isPresent()){
//
//            Member member = result.get();
//            member.changeNickname(memberDTO.getNickname());
//
//            memberRepository.save(member);
//        }
//    }
}
