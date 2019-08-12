package com.airbnb.android.core.responses;

import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class BatchOperation {
    @JsonIgnore
    private Object body;
    public Object convertedResponse;
    @JsonProperty("method")
    String method;
    @JsonProperty("path")
    String path;
    @JsonProperty("query")
    QueryStrap query;
    @JsonProperty("response")
    JsonNode response;

    public BatchOperation(RequestMethod method2, String path2, Object body2, QueryStrap query2) {
        this(parseMethod(method2), path2, body2, query2);
    }

    public BatchOperation(String method2, String path2, Object body2, QueryStrap query2) {
        if (!path2.startsWith("/")) {
            path2 = "/" + path2;
        }
        this.path = path2;
        this.method = method2;
        this.query = query2;
        this.body = body2;
    }

    public BatchOperation() {
    }

    @JsonProperty("body")
    public Object getBody() {
        return this.body;
    }

    @JsonIgnore
    public void setBody(String body2) {
        this.body = body2;
    }

    public JsonNode response() {
        return this.response;
    }

    private static String parseMethod(RequestMethod method2) {
        switch (method2) {
            case GET:
                return "GET";
            case POST:
                return "POST";
            case PUT:
                return "PUT";
            case DELETE:
                return "DELETE";
            default:
                throw new UnsupportedOperationException("method type " + method2 + " not implemented yet for batch requests");
        }
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof BatchOperation)) {
            return false;
        }
        BatchOperation that = (BatchOperation) o;
        if (!this.path.equals(that.path) || !this.method.equals(that.method)) {
            return false;
        }
        if (this.query == null ? that.query != null : !this.query.equals(that.query)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return (((this.path.hashCode() * 31) + this.method.hashCode()) * 31) + (this.query != null ? this.query.hashCode() : 0);
    }

    public String toString() {
        return getClass().getSimpleName() + "{method: " + this.method + ", path: " + this.path + ", query: " + this.query + "}";
    }
}
