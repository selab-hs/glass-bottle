package com.service.core.auth.filter;

import com.service.core.auth.filter.vo.AccessLink;
import com.service.core.auth.infrastructure.LocalContextHolder;
import com.service.core.member.domain.vo.RoleType;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.util.PatternMatchUtils;

@RequiredArgsConstructor
public class LoginCheckFilter implements Filter {

    private final AccessLink accessLink;

    private boolean isLoginCheckPath(String requestURI) {
        return PatternMatchUtils.simpleMatch(accessLink.getPassLinks(), requestURI);
    }

    private boolean isMemberCheckPath(String requestURI) {
        if(LocalContextHolder.getContext().getRoleType().equals(RoleType.USER)){
            for(String access: accessLink.getAccessRoles()){
                if(requestURI.contains(access)){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            if (isLoginCheckPath(requestURI)) {
                chain.doFilter(request, httpResponse);
                return;
            }else if(isMemberCheckPath(requestURI)){
                chain.doFilter(request, httpResponse);
                return;
            }
            throw new ServletException("오류");
        } catch (Exception e) {
            throw e;
        }
    }
}