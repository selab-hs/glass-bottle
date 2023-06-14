package com.service.core.member.domain.vo;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.member.EmailFormatMismatchException;
import lombok.Getter;

@Getter
public class Email {
    private String email;

    private static final String[] EMAIL_FILTERS = {
        "naver.com",
        "gmail.com",
        "daum.net",
        "kakao.com"};
    Email(String email){
        this.email = email;
    }

    public static Email of(String email){
        validate(email);
        return new Email(email);
    }

    private static void validate(String email){
        String[] emails = email.split("@");
        for(String validateEmailForm : EMAIL_FILTERS){
            if(validateEmailForm.equals(emails[1])){
                return;
            }
        }
        throw new EmailFormatMismatchException(ErrorMessage.EMAIL_FORMAT_MISMATCH_ERROR);
    }
}
