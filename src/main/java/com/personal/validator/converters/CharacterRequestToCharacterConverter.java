package com.personal.validator.converters;

import com.personal.validator.models.Character;
import com.personal.validator.models.CharacterRequest;
import com.personal.validator.models.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CharacterRequestToCharacterConverter implements Converter<CharacterRequest, Character> {

    @Override
    public Character convert(CharacterRequest characterRequest) {

        final Character character = new Character();
        character.setName(characterRequest.name());
        character.setHealthPoints(characterRequest.healthPoints());
        character.setAttack(characterRequest.attack());
        character.setDefense(characterRequest.defense());
        character.setStamina(characterRequest.stamina());
        character.setIntelligence(characterRequest.intelligence());
        character.setStatus(Status.valueOf(characterRequest.status()));

        return character;
    }
}
