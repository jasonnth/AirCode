package com.apollographql.apollo.api;

import com.apollographql.apollo.api.internal.Utils;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class Response<T> {
    private final T data;
    private Set<String> dependentKeys;
    private final List<Error> errors;
    private final C3107Operation operation;

    public static final class Builder<T> {
        /* access modifiers changed from: private */
        public T data;
        /* access modifiers changed from: private */
        public Set<String> dependentKeys;
        /* access modifiers changed from: private */
        public List<Error> errors;
        /* access modifiers changed from: private */
        public final C3107Operation operation;

        Builder(C3107Operation operation2) {
            this.operation = (C3107Operation) Utils.checkNotNull(operation2, "operation == null");
        }

        public Builder<T> data(T data2) {
            this.data = data2;
            return this;
        }

        public Builder<T> errors(List<Error> errors2) {
            this.errors = errors2;
            return this;
        }

        public Builder<T> dependentKeys(Set<String> dependentKeys2) {
            this.dependentKeys = dependentKeys2;
            return this;
        }

        public Response<T> build() {
            return new Response<>(this);
        }
    }

    public static <T> Builder<T> builder(C3107Operation operation2) {
        return new Builder<>(operation2);
    }

    Response(Builder<T> builder) {
        Set<String> emptySet;
        this.operation = (C3107Operation) Utils.checkNotNull(builder.operation, "operation == null");
        this.data = builder.data;
        this.errors = builder.errors != null ? Collections.unmodifiableList(builder.errors) : Collections.emptyList();
        if (builder.dependentKeys != null) {
            emptySet = Collections.unmodifiableSet(builder.dependentKeys);
        } else {
            emptySet = Collections.emptySet();
        }
        this.dependentKeys = emptySet;
    }

    public T data() {
        return this.data;
    }

    public List<Error> errors() {
        return this.errors;
    }

    public boolean hasErrors() {
        return !this.errors.isEmpty();
    }
}
