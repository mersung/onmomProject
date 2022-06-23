package org.zerock.onmomProject.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.repository.MemberRepository;
import org.zerock.onmomProject.security.dto.OnmomAuthMemberDTO;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class OnmomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("OnmomUserDetailsService loadUserByUsername " + username);

        Optional<Member> result= memberRepository.findByEmail(username);

        Member member = result.get();

        log.info("--------------------------------------");
        log.info(member);

        OnmomAuthMemberDTO onmomAuthMember = new OnmomAuthMemberDTO(
                member.getMember_id(),
                member.getPw(),
                member.getNickname(),
                member.getRoleSet().stream().map(role
                        -> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toList())
        );

        return onmomAuthMember;
    }
}
