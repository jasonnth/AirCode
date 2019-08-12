package com.apollographql.apollo.api;

import java.io.IOException;

public interface ResponseFieldMapper<T> {
    T map(ResponseReader responseReader) throws IOException;
}
