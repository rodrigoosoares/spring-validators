package com.personal.validator.controllers;

import com.personal.validator.LoadPayload;
import com.personal.validator.models.Character;
import com.personal.validator.services.CharacterService;
import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = CharacterController.class)
class CharacterControllerTest {

    private static final String CHARACTER_ENDPOINT = "/character";
    private static final String PAYLOADS_CHARACTER_REQUEST = "payloads/character/request";
    private static final String PAYLOADS_CHARACTER_RESPONSE = "payloads/character/response";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    @Test
    @DisplayName("[200] POST - Create new Character")
    void should_ExecuteTheCharacterCreation_When_ReceiveAValidBody() throws Exception {

        final String requestBodyPayload = LoadPayload.from(PAYLOADS_CHARACTER_REQUEST, "valid-character-creation");
        final String expectedResponse = LoadPayload.from(PAYLOADS_CHARACTER_RESPONSE, "valid-character-creation-response");

        when(characterService.createCharacter(any(Character.class))).thenReturn(1234L);

        mockMvc.perform(post(CHARACTER_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBodyPayload))
                .andExpect(status().isOk())
                .andDo(result -> assertResponse(expectedResponse, result.getResponse().getContentAsString()));

    }

    @Test
    @DisplayName("[400] POST - Receive invalid status when create new Character")
    void should_ReturnBadRequest_When_ReceiveAnInvalidStatus() throws Exception {

        final String requestBodyPayload = LoadPayload.from(PAYLOADS_CHARACTER_REQUEST, "invalid-status-character-creation");
        final String expectedResponse = LoadPayload.from(PAYLOADS_CHARACTER_RESPONSE, "invalid-status-character-creation-response");

        mockMvc.perform(post(CHARACTER_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBodyPayload))
                .andExpect(status().isBadRequest())
                .andDo(result -> assertResponse(expectedResponse, result.getResponse().getContentAsString()));

    }

    private void assertResponse(String expectedResponse, String contentAsString) throws JSONException {

        JSONAssert.assertEquals(expectedResponse, contentAsString, JSONCompareMode.LENIENT);
    }

}