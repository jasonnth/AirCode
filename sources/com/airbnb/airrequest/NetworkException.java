package com.airbnb.airrequest;

import okhttp3.Response;
import okhttp3.ResponseBody;

public interface NetworkException {
    String bodyString();

    <T> T errorResponse();

    String getMessage();

    boolean hasErrorResponse();

    Response rawResponse();

    ResponseBody responseBody();

    int statusCode();
}
