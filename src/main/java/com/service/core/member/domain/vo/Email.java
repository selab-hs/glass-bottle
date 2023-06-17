package com.service.core.member.domain.vo;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.member.EmailFormatMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Getter;

@Getter
public class Email {
    private final String email;
    private static final String regex = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public Email(String email){
        validate(email);
        this.email = email;
    }

    private static void validate(String email){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            throw new EmailFormatMismatchException(ErrorMessage.EMAIL_FORMAT_MISMATCH_ERROR);
        }
    }
}