package com.service.core.mbti.infrastructure;

import com.service.core.mbti.domain.MbtiQuizHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MbtiQuizHistoryRepository extends JpaRepository<MbtiQuizHistory, Long> {
    void deleteByRoundId(Long id);

    List<MbtiQuizHistory> findByRoundId(Long roundId);
    List<MbtiQuizHistory> findByUserIdAndRoundId(Long userId, Long RoundId);
    List<MbtiQuizHistory> findByRoundIdAndMbtiMetadataId(Long roundId, Long mbtiMetadataId);
}
