package com.apollographql.apollo;

import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloCanceledException;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.exception.ApolloHttpException;
import com.apollographql.apollo.exception.ApolloNetworkException;
import com.apollographql.apollo.exception.ApolloParseException;

public interface ApolloCall<T> {

    public static abstract class Callback<T> {
        public abstract void onFailure(ApolloException apolloException);

        public abstract void onResponse(Response<T> response);

        public void onHttpError(ApolloHttpException e) {
            onFailure(e);
            okhttp3.Response response = e.rawResponse();
            if (response != null) {
                response.close();
            }
        }

        public void onNetworkError(ApolloNetworkException e) {
            onFailure(e);
        }

        public void onParseError(ApolloParseException e) {
            onFailure(e);
        }

        public void onCanceledError(ApolloCanceledException e) {
            onFailure(e);
        }
    }

    void enqueue(Callback<T> callback);
}
