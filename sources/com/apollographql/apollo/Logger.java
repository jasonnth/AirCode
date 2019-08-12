package com.apollographql.apollo;

import com.apollographql.apollo.api.internal.Optional;

public interface Logger {
    void log(int i, String str, Optional<Throwable> optional, Object... objArr);
}
