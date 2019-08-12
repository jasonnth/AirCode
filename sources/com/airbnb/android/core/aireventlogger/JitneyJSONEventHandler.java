package com.airbnb.android.core.aireventlogger;

import android.content.Context;
import com.airbnb.android.aireventlogger.AirEvent;
import com.airbnb.android.aireventlogger.CompressionType;
import com.airbnb.android.aireventlogger.Converter.Factory;
import com.airbnb.android.aireventlogger.StandardEventHandler;

public final class JitneyJSONEventHandler extends StandardEventHandler {
    private static final String JITNEY_JSON_TABLE = "jitney_json";

    public JitneyJSONEventHandler(Context context, Factory converterFactory, String baseUrl, CompressionType compressionType) {
        super(context, converterFactory, baseUrl, compressionType, "jitney_json");
    }

    public <T> boolean supports(AirEvent<T> event) {
        return event.target() != null && event.target().equals("jitney_json");
    }
}
