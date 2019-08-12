package com.apollographql.apollo.exception;

import okhttp3.Response;

public final class ApolloHttpException extends ApolloException {
    private final int code;
    private final String message;
    private final transient Response rawResponse;

    public ApolloHttpException(Response rawResponse2) {
        super(formatMessage(rawResponse2));
        this.code = rawResponse2 != null ? rawResponse2.code() : 0;
        this.message = rawResponse2 != null ? rawResponse2.message() : "";
        this.rawResponse = rawResponse2;
    }

    public Response rawResponse() {
        return this.rawResponse;
    }

    private static String formatMessage(Response response) {
        if (response == null) {
            return "Empty HTTP response";
        }
        return "HTTP " + response.code() + " " + response.message();
    }
}
