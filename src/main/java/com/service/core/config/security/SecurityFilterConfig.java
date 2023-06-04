package com.service.core.config.security;

import com.service.core.auth.filter.JwtAuthenticationFilter;
import com.service.core.auth.filter.LoginCheckFilter;
import com.service.core.auth.filter.vo.AccessLink;
import com.service.core.auth.token.TokenProvider;
import javax.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SecurityFilterConfig {

    private final TokenProvider tokenProvider;
    private final AccessLink accessLink;

    @Bean
    public FilterRegistrationBean<Filter> JwtAuthenticationCheckFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new JwtAuthenticationFilter(tokenProvider));
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addUrlPatterns("/*");

        return filterFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter> loginCheckFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LoginCheckFilter(accessLink));
        filterFilterRegistrationBean.setOrder(2);
        filterFilterRegistrationBean.addUrlPatterns("/*");

        return filterFilterRegistrationBean;
    }
}