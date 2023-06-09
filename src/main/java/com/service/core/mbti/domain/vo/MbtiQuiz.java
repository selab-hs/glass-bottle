package com.service.core.mbti.domain.vo;

import com.service.core.error.dto.ErrorMessage;
import com.service.core.error.exception.mbti.NotEqualRoundSizeException;
import com.service.core.error.exception.mbti.NotEqualRoundsSizeException;
import com.service.core.error.exception.mbti.UnacceptableScoreFormException;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MbtiQuiz {
    private final static int E_I_ROUND_SIZE = 8;
    private final static int S_N_ROUND_SIZE = 8;
    private final static int T_F_ROUND_SIZE = 8;
    private final static int J_P_ROUND_SIZE = 8;

    public static final int[] mbtiProblemsLengths = {E_I_ROUND_SIZE, S_N_ROUND_SIZE, T_F_ROUND_SIZE, J_P_ROUND_SIZE};
    public static final String[][] mbtiTypes = {{"E","I"}, {"S","N"}, {"T","F"}, {"J","P"}};

    public String resultMbti(int[][] answer){
        validateMbti(answer);
        return checkMbtiType(answer);
    }

    private int mbtiRoundSum(int[] round){
        int sum = 0;
        for(int num : round){
            sum += num;
        }
        return sum;
    }

    private String checkMbtiType(int[][] answer){
        String mbtiResult = "mbti";
        for(int i=0; i<answer.length;i++){
                int sum = mbtiRoundSum(answer[i]);
                mbtiResult =
                    sum / mbtiProblemsLengths[i] > 4 ?
                        mbtiResult+ mbtiTypes[i][0] : mbtiResult+ mbtiTypes[i][1];
        }
        return mbtiResult.substring(4);
    }

    private void validateMbti(int[][] answer){
        if(answer.length != mbtiProblemsLengths.length){
            throw new NotEqualRoundsSizeException(ErrorMessage.NOT_EQUAL_ROUNDS_SIZE_ERROR);
        }
        for(int i =0; i<answer.length; i++){
            if(answer[i].length != mbtiProblemsLengths[i]){
                throw new NotEqualRoundSizeException(ErrorMessage.NOT_EQUAL_ROUND_SIZE_ERROR);
            }
            for (int initScore : answer[i]) {
              if(0 > initScore || initScore>8){
                  throw new UnacceptableScoreFormException(ErrorMessage.UNACCEPTABLE_SCORE_FORM_ERROR);
              }
            }
        }
    }
}