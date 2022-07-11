package org.zerock.onmomProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.onmomProject.dto.MemberDTO;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.repository.MemberRepository;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    //회원 정보 불러오기
    @Override
    public MemberDTO selectMember(String member_id) {
        Optional<Member> member = memberRepository.findByEmail(member_id);
        return member.isPresent() ? entityToDto(member.get()) : null;
    }

}
