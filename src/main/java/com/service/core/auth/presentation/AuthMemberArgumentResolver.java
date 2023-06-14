package com.service.core.auth.presentation;

import com.service.core.auth.infrastructure.authenticaionStrategy.DefaultAuthenticationStrategy;
import com.service.core.auth.infrastructure.annotation.AuthMember;
import com.service.core.member.convert.MemberConvert;
import com.service.core.member.dto.response.UserInfo;
import com.service.core.member.infrastructure.MemberRepository;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
@RequiredArgsConstructor
public class AuthMemberArgumentResolver implements HandlerMethodArgumentResolver {

    private final DefaultAuthenticationStrategy getAuthenticationStrategy;
    private final MemberRepository userRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Objects.nonNull(parameter.getParameterAnnotation(AuthMember.class));
    }

    @Override
    public Object resolveArgument(
        MethodParameter parameter,
        ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory){
        var httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        var userData = userInfo(httpServletRequest);
        if (parameter.getParameterType() == Optional.class) {
            return userData;
        }
        return userData.orElseThrow(NullPointerException::new);
    }

    private Optional<UserInfo> userInfo(HttpServletRequest request){
        Long authentication = getAuthenticationStrategy.get(request).getUserDetail().getId();
        var users = userRepository.findById(authentication);
        return Optional.ofNullable(MemberConvert.toUserInfo(users.get()));
    }
}