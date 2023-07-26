package com.service.core.member.domain.vo;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.member.PasswordMismatchException;
import lombok.Getter;

@Getter
public class Password {

    private final String password;

    public Password(String password){
        validate(password);
        this.password = password;
    }

    private static void validate(String password){
        if(password.length()<3){
         throw new PasswordMismatchException(ErrorMessage.PASSWORD_FORMAT_MISMATCH_ERROR, "비밀번호 형식이 맞지 않습니다.");
        }
    }
}