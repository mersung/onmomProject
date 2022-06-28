package org.zerock.onmomProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.onmomProject.dto.MemberDTO;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.repository.MemberRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public MemberDTO save(MemberDTO memberDTO) throws IOException {
        String member_id = memberDTO.getMember_id();
        memberDTO.setMember_id(member_id);

        Member member = dtoToEntity(memberDTO);
        Member validUser = memberRepository.save(member);
        MemberDTO dto = entityToDto(validUser);
        return dto;
    }

    @Override
    public MemberDTO selectMember(String member_id) {
        Optional<Member> member = memberRepository.findByEmail(member_id);
        return member.isPresent() ? entityToDto(member.get()) : null;
    }
}
