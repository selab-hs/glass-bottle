package com.service.core.letter.application;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.service.core.letter.dto.request.WriteLetterRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest
@Transactional
@Slf4j
class SendLetterServiceTest {
    @Test
    public void 편지_작성_테스트() {
        //given
        FixtureMonkey sut = FixtureMonkey.create();

        //when
        var letter = sut.giveMeOne(WriteLetterRequest.class);

        log.info("letter title: " + letter.getTitle());

        //then
        then(letter.getTitle()).isNotNull();
        then(letter.getTitle().length()).isBetween(1, 100);
    }
}
