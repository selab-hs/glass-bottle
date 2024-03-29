package com.service.core.letter.presentation;

import com.google.gson.Gson;
import com.service.core.letter.application.LetterService;
import com.service.core.letter.dto.request.WriteLetterRequest;
import com.service.core.letter.vo.LetterState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class LetterControllerTest {
    @Mock
    private LetterService letterService;

    @InjectMocks
    private LetterController letterController;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(letterController).build();
    }

    @Test
    void 편지_전송_테스트() throws Exception {
        //given
        var request = WriteLetterRequest.builder()
                .title("test title")
                .content("test content")
                .senderMbtiId(1L)
                .receiverMbtiId(2L)
                .state(LetterState.ACTIVE)
                .build();

        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/letters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request)));

        //then
        resultActions.andExpect(status().isCreated());
    }
}