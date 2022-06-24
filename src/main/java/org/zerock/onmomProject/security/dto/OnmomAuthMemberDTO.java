package org.zerock.onmomProject.security.dto;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Log4j2
public class OnmomAuthMemberDTO extends User {

    private String member_id;
    private String nickname;

    public OnmomAuthMemberDTO(String username,
                              String password,
                              String nickname,
                              Collection<? extends GrantedAuthority> authorities){

        super(username, password, authorities);
        this.member_id=username;
        this.nickname=nickname;
    }
}
