package com.service.core.mbti.application;

import com.service.core.mbti.dto.response.ReadMbtiMetadataResponse;
import com.service.core.mbti.infrastructure.MbtiMetadataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MbtiMetadataService {
    private final MbtiMetadataRepository mbtiMetadataRepository;

    private Map<Long, ReadMbtiMetadataResponse.MetadataModel> mbtiMetadataModels = new TreeMap<>();

    @Scheduled(cron = "* */5 * * * *")
    public void refreshMbtiMetadata() {
        var refreshedMbtiModels = mbtiMetadataRepository.findAll()
                .stream()
                .map(ReadMbtiMetadataResponse.MetadataModel::new)
                .collect(Collectors.toMap(ReadMbtiMetadataResponse.MetadataModel::getId, Function.identity()));

        mbtiMetadataModels = new TreeMap<>(refreshedMbtiModels);

        log.info("refresh Mbti Metadata success");
    }

    public ReadMbtiMetadataResponse getAll() {
        var models = new ArrayList<>(mbtiMetadataModels.values());
        return new ReadMbtiMetadataResponse(models);
    }
}