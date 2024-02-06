package com.personal.validator.services;

import com.personal.validator.models.Character;
import org.springframework.stereotype.Service;

@Service
public class CharacterService {

    public Long createCharacter(final Character character) {

        // Only a mock class with hardcoded return, simulating the database return
        return 1234L;
    }

}
