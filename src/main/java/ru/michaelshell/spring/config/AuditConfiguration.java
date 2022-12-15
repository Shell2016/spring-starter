package ru.michaelshell.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.envers.repository.config.EnableEnversRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.michaelshell.spring.ApplicationRunner;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
@EnableEnversRepositories(basePackageClasses = ApplicationRunner.class)
public class AuditConfiguration {

    @Bean
    public AuditorAware<String> auditorAware() {

        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication.getPrincipal() == "anonymousUser") {
                return Optional.of("new_user_registration");
            }
            return Optional.ofNullable(((UserDetails) authentication.getPrincipal()).getUsername());
        };
//        return () -> Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
//                .map(authentication -> (UserDetails) authentication.getPrincipal())
//                .map(UserDetails::getUsername);
    }
}
