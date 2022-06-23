package org.zerock.onmomProject.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private OnmomUserDetailsService userDetailsService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests().antMatchers("/sample/member").hasRole("USER");

        http.formLogin().loginPage("/sample/login").loginProcessingUrl("/login");

        http.csrf().disable();

//        http.oauth2Login().successHandler(successHandler());
//        http.rememberMe().tokenValiditySeconds(60 * 60 * 7).userDetailsService(userDetailsService);  //7days
        http.logout().logoutSuccessUrl("/login");
//    }

//    @Bean
//    public ClubLoginSuccessHandler successHandler() {
//        return new ClubLoginSuccessHandler(passwordEncoder());
//    }


    }
}

