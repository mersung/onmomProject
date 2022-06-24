package org.zerock.onmomProject.security.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Log4j2
@Getter
@Setter
@ToString
public class OnmomAuthMemberDTO extends User implements OAuth2User {

    private String member_id;
    private String pw;
    private String nickname;

    private Map<String, Object> attr;

    // 이메일 정보 출력
    public OnmomAuthMemberDTO(String username,
                              String password,
                              String nickname,
                              Collection<? extends GrantedAuthority> authorities,
                              Map<String, Object> attr){
        this(username, password, nickname, authorities);
        this.attr = attr;
    }

    public OnmomAuthMemberDTO(String username,
                              String password,
                              String nickname,
                              Collection<? extends GrantedAuthority> authorities){

        super(username, password, authorities);
        this.member_id = username;
        this.pw = password;
        this.nickname = nickname;

    }

    @Override
    public Map<String, Object> getAttributes(){
        return this.attr;
    }


    @Override
    public String getName() {
        return null;
    }
}
