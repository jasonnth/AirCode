package com.airbnb.airrequest;

import com.airbnb.rxgroups.RequestSubscription;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public abstract class GraphQlRequest<T> extends DebugOnlyRequest<T> {
    private final RequestBody body;

    static class RequestBody {
        @JsonProperty("query")
        private final String query;
        @JsonProperty("variables")
        private final Map<String, String> variables;

        RequestBody(String query2, Map<String, String> variables2) {
            this.query = query2;
            this.variables = variables2;
        }
    }

    public /* bridge */ /* synthetic */ RequestSubscription execute(RequestExecutor requestExecutor) {
        return super.execute(requestExecutor);
    }

    protected GraphQlRequest(String query, Map<String, String> variables) {
        this.body = new RequestBody(query, variables);
    }

    public String getPath() {
        return "graphql";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public RequestBody getBody() {
        return this.body;
    }
}
