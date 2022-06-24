package org.zerock.onmomProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.repository.MemberRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;

    //스프링이 로그인 요청을 가로챌 때, username, password변수 두개를 가로채는데
    //password부분 처리는 알아서 함.
    //username이 DB에 있는지만 확인해주면 됨. 그걸 아래 함수에서 함
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member principal = memberRepository.findById(username)
                .orElseThrow(() ->{
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + username);

                });
        return new PrincipalDetail(principal);
    }



}
