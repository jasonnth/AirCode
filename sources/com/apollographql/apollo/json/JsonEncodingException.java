package com.apollographql.apollo.json;

import java.io.IOException;

public final class JsonEncodingException extends IOException {
    public JsonEncodingException(String message) {
        super(message);
    }
}
