package org.zerock.onmomProject.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.zerock.onmomProject.entity.Member;
import org.zerock.onmomProject.repository.MemberRepository;

import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class OnmomOAuth2UserDetailsService extends DefaultOAuth2UserService {

//    // 등록
//    private final MemberRepository repository;
//    // 암호화
//    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        log.info("-------------------------------------");
        log.info("userRequest: " + userRequest);
        // org.springframework.security.oauth2.client.userinfo.OAuth2UserReqeust 객체

        String clientName = userRequest.getClientRegistration().getClientName();

        log.info("clientName: "+clientName);
        log.info(userRequest.getAdditionalParameters());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        log.info("==================================");
        oAuth2User.getAttributes().forEach((k,v) -> {
            log.info(k + ":" + v);
        });

//        String member_id = null;
//
//        if (clientName.equals("Google")){ // 구글 로그인
//            member_id = oAuth2User.getAttributes("member_id");
//        }
//
//        log.info("MEMBER_ID: " + member_id);
//
//        Member member = saveSocialMember(member_id);

        return oAuth2User;
    }

//    private Member saveSocialMember(String member_id) {
//
//        // 기존에 동일한 이메일로 가입한 회원 조회
//        Optional<Member> result = repository.findByEmail(member_id);
//
//        if (result.isPresent()){
//            return result.get();
//        }


//    }
}
