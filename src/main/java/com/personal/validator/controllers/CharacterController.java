package com.personal.validator.controllers;

import com.personal.validator.converters.CharacterRequestToCharacterConverter;
import com.personal.validator.models.Character;
import com.personal.validator.models.CharacterRequest;
import com.personal.validator.services.CharacterService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/character")
@Validated
public class CharacterController {

    private final CharacterService characterService;
    private final CharacterRequestToCharacterConverter characterRequestToCharacterConverter;

    public CharacterController(CharacterService characterService, CharacterRequestToCharacterConverter characterRequestToCharacterConverter) {

        this.characterService = characterService;
        this.characterRequestToCharacterConverter = characterRequestToCharacterConverter;
    }

    @PostMapping
    public ResponseEntity<Long> createCharacter(@RequestBody @Valid final CharacterRequest characterRequest) {

        final Character character = characterRequestToCharacterConverter.convert(characterRequest);

        final Long createdCharacterId = characterService.createCharacter(character);

        return ResponseEntity.ok(createdCharacterId);
    }

}
