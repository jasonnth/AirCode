package com.apollographql.apollo.internal.util;

import com.apollographql.apollo.Logger;
import com.apollographql.apollo.api.internal.Optional;
import com.apollographql.apollo.api.internal.Utils;

public final class ApolloLogger {
    private final Optional<Logger> logger;

    public ApolloLogger(Optional<Logger> logger2) {
        this.logger = (Optional) Utils.checkNotNull(logger2, "logger == null");
    }

    /* renamed from: d */
    public void mo27164d(String message, Object... args) {
        log(3, message, null, args);
    }

    /* renamed from: d */
    public void mo27165d(Throwable t, String message, Object... args) {
        log(3, message, t, args);
    }

    /* renamed from: w */
    public void mo27168w(Throwable t, String message, Object... args) {
        log(5, message, t, args);
    }

    /* renamed from: e */
    public void mo27166e(String message, Object... args) {
        log(6, message, null, args);
    }

    /* renamed from: e */
    public void mo27167e(Throwable t, String message, Object... args) {
        log(6, message, t, args);
    }

    private void log(int priority, String message, Throwable t, Object... args) {
        if (this.logger.isPresent()) {
            ((Logger) this.logger.get()).log(priority, message, Optional.fromNullable(t), args);
        }
    }
}
