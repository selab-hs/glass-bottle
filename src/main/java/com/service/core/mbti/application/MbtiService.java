package com.service.core.mbti.application;

import com.service.core.mbti.domain.converter.MbtiResultConverter;
import com.service.core.mbti.dto.request.CreateMyMbtiRequest;
import com.service.core.mbti.infrastructure.MbtiMetadataRepository;
import com.service.core.mbti.infrastructure.MemberMbtiRepository;
import com.service.core.member.dto.response.UserInfo;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MbtiService {
    private final MemberMbtiRepository memberMbtiRepository;
    private final MbtiMetadataRepository mbtiMetadataRepository;
    private final MbtiResultConverter converter;

    @Transactional
    public String mbtiQuizResultSave(UserInfo user, CreateMyMbtiRequest request) {
        String mbtiName = converter.toMbtiQuizResult(request.getMbtiRequest());
        memberMbtiRepository.save(
            converter.convertToMemberMbti(
                user.getId(),
                mbtiMetadataRepository.findByType(mbtiName).get()
            )
        );
    return mbtiName;
    }
}