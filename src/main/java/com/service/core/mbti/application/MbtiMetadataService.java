package com.service.core.mbti.application;

import com.service.core.mbti.dto.response.MbtiMetadataResponse;
import com.service.core.mbti.infrastructure.MbtiMetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MbtiMetadataService {
    private final MbtiMetadataRepository mbtiMetadataRepository;

    public MbtiMetadataResponse getAll() {
        var models = mbtiMetadataRepository.findAll()
                .stream()
                .map(MbtiMetadataResponse.MetadataModel::new)
                .collect(Collectors.toList());

        return new MbtiMetadataResponse(models);
    }
}
