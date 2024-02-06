package com.personal.validator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class LoadPayload {

    private LoadPayload() {

    }

    public static String from(final String path, final String fileName) throws IOException {

        final InputStream fileStream = LoadPayload.class.getClassLoader().getResourceAsStream(String.format("%s/%s.json", path, fileName));

        if(Objects.nonNull(fileStream)) {

            return new String(fileStream.readAllBytes());
        }

        throw new IOException("Could not read file.");
    }
}
