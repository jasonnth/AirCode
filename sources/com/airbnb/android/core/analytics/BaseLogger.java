package com.airbnb.android.core.analytics;

import com.airbnb.android.core.JitneyPublisher;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;

public abstract class BaseLogger {
    protected final LoggingContextFactory loggingContextFactory;

    protected BaseLogger(LoggingContextFactory loggingContextFactory2) {
        this.loggingContextFactory = loggingContextFactory2;
    }

    /* access modifiers changed from: protected */
    public Context context() {
        return this.loggingContextFactory.newInstance();
    }

    /* access modifiers changed from: protected */
    public void publish(StructBuilder<? extends Struct> structBuilder) {
        JitneyPublisher.publish(structBuilder);
    }
}
