package com.matheusncodello.applicationws.infra.configurations.security;

import com.matheusncodello.applicationws.service.user.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    private static final String[] PUBLIC_URI = {
            "/user/register", "/user/login",
            "/user/get-post-history", "/h2-console/**",
            "/swagger-ui/**", "/swagger-resources/**", "/swagger-ui.html", "/v2/api-docs", "/webjars/**",
            "/campaign/register-user", "/campaign/get-campaign-txt"
    };

    private static final String[] AUTH_URI = {
            "/user/logoff", "/user/register-post-access",
            "/donation/get-donation", "/campaign/get-campaign",
            "/donation/get-single-donation", "/campaign/get-single-campaign",
            "/donation/get-user-donation", "/campaign/get-ong-campaign",
            "/donation/count-donation-access", "/campaign/count-campaign-access"
    };

    private static final String[] USER_URI = {
            "/donation/create-donation",
            "/donation/edit-donation",
            "/donation/end-donation",
            "/campaign/get-favorite-campaign",
            "/campaign/favorite-campaign",
            "/campaign/unfavorite-campaign"
    };

    private static final String[] ONG_URI = {
            "/campaign/create-campaign",
            "/campaign/edit-campaign",
            "/campaign/end-campaign",
            "/donation/get-favorite-donation",
            "/donation/favorite-donation",
            "/donation/unfavorite-donation"
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(PUBLIC_URI)
                    .permitAll()
                .antMatchers(AUTH_URI)
                    .hasAnyRole("ONG", "USER")
                .antMatchers(ONG_URI)
                    .hasRole("ONG")
                .antMatchers(USER_URI)
                    .hasRole("USER")
                .anyRequest()
                    .authenticated()
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint())
                    .accessDeniedHandler(accessDeniedHandler())
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .logout()
                .deleteCookies("remove")
                .invalidateHttpSession(true);

    }

    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint(){
        return new ForbiddenEntryPoint();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new ForbiddenEntryPoint();
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
