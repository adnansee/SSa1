package com.example.security.demo.security;
import com.example.security.demo.model.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

    @Configurable
    @EnableWebSecurity
    public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Qualifier("myUserDetailsService")
        @Autowired
        UserDetailsService userDetailsService;

        @Autowired
        PasswordEncoder passwordEncoder;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {

            auth.userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers(HttpMethod.GET,"/admin/**").hasRole("ADMIN")
                    .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                    .antMatchers(HttpMethod.GET,"/user/**").hasAnyRole("USER", "ADMIN")
                    .antMatchers(HttpMethod.GET,"/").permitAll()
                    .and().logout().logoutSuccessUrl("/user").permitAll()
                   .and().httpBasic().and().csrf().disable().formLogin();
        }

        @Bean
        public PasswordEncoder getPasswordEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }


    }
