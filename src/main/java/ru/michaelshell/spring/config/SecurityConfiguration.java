package ru.michaelshell.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration /*extends WebSecurityConfigurerAdapter*/ {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests().anyRequest().authenticated();
//        http.formLogin(login -> login
//                .loginPage("/login")
//                .defaultSuccessUrl("/users")
//                .permitAll());
//    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().anyRequest().authenticated();
        http.formLogin(login -> login
                .loginPage("/login")
                .defaultSuccessUrl("/users")
                .permitAll());
        return http.build();
    }
}
