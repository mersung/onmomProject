package org.zerock.onmomProject.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.zerock.onmomProject.repository.MemberRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class OnmomAuthUserDetailsService extends DefaultOAuth2UserService {

    // 등록
    private final MemberRepository repository;
    // 암호화
    private final PasswordEncoder passwordEncoder;

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

        return oAuth2User;
    }
}
