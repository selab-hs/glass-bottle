package com.service.core.auth.infrastructure;

import com.service.core.auth.domain.Authentication;
import org.springframework.stereotype.Component;

@Component
public class LocalContextHolder {
    private static final ThreadLocal<Authentication> context = new ThreadLocal<>();

    public static Authentication getContext (){
        return context.get();
    }
    public static void setContext(Authentication authentication){
        context.set(authentication);
    }

    public static void remove(){
        context.remove();
    }
}