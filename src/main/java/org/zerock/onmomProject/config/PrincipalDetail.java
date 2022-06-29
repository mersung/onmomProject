package org.zerock.onmomProject.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.zerock.onmomProject.entity.Member;

import java.util.ArrayList;
import java.util.Collection;

// 스프링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 userDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션장소에 저장을 해준다.
@Getter
@RequiredArgsConstructor
public class PrincipalDetail implements UserDetails {

    private Member user; //컴포지션(객체를 품고있음)


    public PrincipalDetail(Member user){
        this.user = user;

    }


    //계정의 권한을 리턴해준다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //ArrayList의 부모는 Collection이다
        Collection<GrantedAuthority> collectors = new ArrayList<>();
//		collectors.add(new GrantedAuthority() {
//
//			@Override
//			public String getAuthority() {
//				// 스프링에서 ROLE을 받을 때 규칙, ROLE을 꼭 넣어줘야함.
//				return "ROLE_"+user.getRole(); //ROLE_USER 리턴해줘야 인식 가능
//			}
//		});

        collectors.add(()->{return "ROLE_"+user.getRoleSet();});

        return collectors;
    }

    @Override
    public String getPassword() {
        return user.getPw();
    }

    @Override
    public String getUsername() {
        return user.getMember_id();
    }

    // 계정이 만료되지 않았는지 리턴해준다
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있는지 리턴해준다.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료? 리턴
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 활성화(사용 가능)?
    @Override
    public boolean isEnabled() {
        return true;
    }
}
