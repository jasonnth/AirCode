package com.airbnb.android.core.graphql;

import com.apollographql.apollo.api.Error;
import com.apollographql.apollo.api.Response;
import com.google.common.base.Optional;
import java.util.List;

public class GraphistResponse<D> {
    private final Optional<D> data;
    private final Optional<Throwable> error;
    private final boolean fromCache;
    private final List<Error> graphQLErrorList;

    public static <D> GraphistResponse<D> fromApolloResponse(Response<Optional<D>> response, boolean fromCache2) {
        return fromApolloResponse(response, fromCache2, null);
    }

    public static <D> GraphistResponse<D> fromApolloResponse(Response<Optional<D>> response, boolean fromCache2, Throwable error2) {
        return new GraphistResponse<>((Optional) response.data(), response.errors(), fromCache2, error2);
    }

    public static <D> GraphistResponse<D> fromGraphistResponse(GraphistResponse<D> response, boolean fromCache2) {
        return fromGraphistResponse(response, fromCache2, null);
    }

    public static <D> GraphistResponse<D> fromGraphistResponse(GraphistResponse<D> response, boolean fromCache2, Throwable error2) {
        return new GraphistResponse<>(response.data(), response.graphQLErrorList(), fromCache2, error2);
    }

    private GraphistResponse(Optional<D> data2, List<Error> graphQLErrorList2, boolean fromCache2, Throwable error2) {
        this.data = data2;
        this.graphQLErrorList = graphQLErrorList2;
        this.fromCache = fromCache2;
        this.error = Optional.fromNullable(error2);
    }

    public boolean hasErrors() {
        return hasGraphQLErrors() || this.error.isPresent();
    }

    public boolean hasGraphQLErrors() {
        return !this.graphQLErrorList.isEmpty();
    }

    public Optional<D> data() {
        return this.data;
    }

    private Optional<Throwable> error() {
        return this.error;
    }

    public List<Error> graphQLErrorList() {
        return this.graphQLErrorList;
    }

    public boolean fromCache() {
        return this.fromCache;
    }

    public String errorMessage() {
        StringBuilder errorBuilder = new StringBuilder("{");
        if (this.error.isPresent()) {
            errorBuilder.append("error={").append(((Throwable) this.error.get()).getMessage()).append("}");
            if (!this.graphQLErrorList.isEmpty()) {
                errorBuilder.append(", ");
            }
        }
        if (!this.graphQLErrorList.isEmpty()) {
            errorBuilder.append("gqlErrors=[");
            for (Error error2 : graphQLErrorList()) {
                errorBuilder.append("{").append(error2.toString()).append("}");
            }
            errorBuilder.append("]");
        }
        errorBuilder.append("}");
        return errorBuilder.toString();
    }
}
