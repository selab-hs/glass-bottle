package com.service.core.mbti.domain.vo;

import com.service.core.mbti.domain.MbtiMetadata;
import com.service.core.mbti.domain.MbtiQuizHistory;
import java.util.Hashtable;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MbtiRoundResult {
    private final Hashtable<Long, Integer> result = new Hashtable<>();

    public MbtiRoundResult(List<MbtiQuizHistory> answers, List<MbtiMetadata> mbtis){
        setResultInfo(mbtis);
        setQuizHistoryResult(answers);
    }

    private void setResultInfo(List<MbtiMetadata> mbtis){
        for(MbtiMetadata mbti : mbtis){
            result.put(mbti.getId(), result.getOrDefault(mbti.getId(),0));
        }
    }

    private void setQuizHistoryResult(List<MbtiQuizHistory> answers){
        for(MbtiQuizHistory answer : answers){
            result.put(answer.getMbtiMetadataId(), result.getOrDefault(answer.getMbtiMetadataId(),0)+answer.getAnswer());
        }
    }

    public Hashtable<Long, Integer> getResult(){
        return result;
    }
}