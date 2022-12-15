package ru.michaelshell.spring.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import ru.michaelshell.spring.database.entity.Role;
import ru.michaelshell.spring.dto.UserCreateEditDto;
import ru.michaelshell.spring.service.UserService;
import ru.michaelshell.spring.utils.ApplicationUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration /*extends WebSecurityConfigurerAdapter*/ {

    private final UserService userService;

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
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
                .authorizeHttpRequests(urlConfig -> urlConfig
                        .antMatchers("/login",
                                "/users/registration",
                                "/users/create",
                                "/v3/api-docs/**",
                                "/swagger-ui/**").permitAll()
                        .antMatchers("/admin/**",
                                "/users/{\\d+}/delete").hasAuthority(Role.ADMIN.getAuthority())
                        .anyRequest().authenticated()

                )
//                .httpBasic(Customizer.withDefaults());
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login"))
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/users"))
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/users")
                        .userInfoEndpoint(userInfo -> userInfo.oidcUserService(oidcUserService())));
        return http.build();
    }

    private OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        return userRequest -> {
            String email = userRequest.getIdToken().getClaim("email");

            try {

                return getOidcUser(userRequest, email);

            } catch (UsernameNotFoundException e) {

                UserCreateEditDto defaultUser = new UserCreateEditDto(
                        email,
                        "{noop}0000",
                        null,
                        email,
                        null,
                        Role.USER,
                        4,
                        ApplicationUtils.getEmptyImage()
                );
                userService.create(defaultUser);

                return getOidcUser(userRequest, email);
            }
//            OidcUser oidcUser = new OidcUserService().loadUser(userRequest);

        };
    }

    private OidcUser getOidcUser(OidcUserRequest userRequest, String email) {
        UserDetails userDetails = userService.loadUserByUsername(email);

        DefaultOidcUser oidcUser = new DefaultOidcUser(userDetails.getAuthorities(), userRequest.getIdToken());

        Set<Method> userDetailsMethods = Set.of(UserDetails.class.getMethods());

        return (OidcUser) Proxy.newProxyInstance(SecurityConfiguration.class.getClassLoader(),
                new Class[]{UserDetails.class, OidcUser.class},
                (proxy, method, args) -> userDetailsMethods.contains(method)
                        ? method.invoke(userDetails, args)
                        : method.invoke(oidcUser, args));
    }

}
