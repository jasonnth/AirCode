package com.airbnb.android.core;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;

public final class JitneyPublisher {
    private JitneyPublisher() {
    }

    public static void publish(StructBuilder<? extends Struct> structBuilder) {
        try {
            AirbnbEventLogger.track((Struct) structBuilder.build());
        } catch (RuntimeException e) {
            BugsnagWrapper.throwOrNotify(e);
        }
    }

    public static void publishImmediately(StructBuilder<? extends Struct> structBuilder) {
        try {
            AirbnbEventLogger.trackImmediately((Struct) structBuilder.build());
        } catch (RuntimeException e) {
            BugsnagWrapper.throwOrNotify(e);
        }
    }
}
