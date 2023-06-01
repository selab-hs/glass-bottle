package com.service.core.mbti.presentation;

import com.service.core.mbti.application.MbtiMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = {"/api/v1/mbti/metadata"}, produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class MbtiMetadataController {
    private final MbtiMetadataService mbtiMetadataService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        var response = mbtiMetadataService.getAll();
        return ResponseEntity.ok(response);
    }
}
