package com.service.core.mbti.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyMbtiRequest {
    private String round;
    private int[][] mbtiRequest;
}