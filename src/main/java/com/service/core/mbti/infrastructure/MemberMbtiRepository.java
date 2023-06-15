package com.service.core.mbti.infrastructure;

import com.service.core.mbti.domain.MemberMbti;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMbtiRepository extends JpaRepository<MemberMbti, Long> {

}