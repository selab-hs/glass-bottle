package com.service.core.mbti.infrastructure;

import com.service.core.mbti.domain.MbtiMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MbtiMetadataRepository extends JpaRepository<MbtiMetadata, Long> {
}
