package com.service.core.letter.application;


import com.service.core.common.utis.MapperUtil;
import com.service.core.letter.dto.response.SharingResponse;
import com.service.core.letter.dto.response.WriteLetterResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class SharingLetterTest {

    @InjectMocks
    private LetterService letterservice;

    @Test
    @DisplayName("편지 공유 단위테스트")
    void sharingLetter() {
        //given
        WriteLetterResponse senderLetter = WriteLetterResponse.builder()
                .title("testTitle1")
                .content("testContent1")
                .senderMbtiId(1L)
                .build();

        WriteLetterResponse receiveLetter = WriteLetterResponse.builder()
                .title("testTitle2")
                .content("testContent2")
                .receiverMbtiId(2L)
                .build();

        //when
        String sharingLetterToJson;
        sharingLetterToJson = MapperUtil.writeValueAsString(new SharingResponse(senderLetter, receiveLetter));

        //then
        assertDoesNotThrow(() -> letterservice.convertQRString(sharingLetterToJson));
    }

}
