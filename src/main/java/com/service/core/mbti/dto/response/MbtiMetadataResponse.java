package com.service.core.mbti.dto.response;

import com.service.core.mbti.domain.MbtiMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class MbtiMetadataResponse {
    private final List<MetadataModel> metadataModels;

    @Data
    @AllArgsConstructor
    public static class MetadataModel {
        private Long id;
        private String name;
        private String description;

        public MetadataModel(MbtiMetadata metadata) {
            this.id = metadata.getId();
            this.name = metadata.getName();
            this.description = metadata.getDescription();
        }
    }
}
