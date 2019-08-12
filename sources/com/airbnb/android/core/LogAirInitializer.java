package com.airbnb.android.core;

import android.content.Context;
import com.airbnb.android.aireventlogger.CompressionType;
import com.airbnb.android.aireventlogger.JitneyEventHandler;
import com.airbnb.android.aireventlogger.LogAir;
import com.airbnb.android.aireventlogger.LogAir.Builder;
import com.airbnb.android.aireventlogger.StandardEventHandler;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.aireventlogger.JitneyJSONEventHandler;
import com.airbnb.android.core.data.ConverterFactory;
import com.airbnb.android.core.utils.MemoryUtils;
import dagger.Lazy;

public final class LogAirInitializer {
    private static final String AIREVENTS_ENDPOINT = "https://www.airbnb.com/tracking/events";
    private static final String JITNEY_ENDPOINT = "https://www.airbnb.com/tracking/jitney/logging/messages";
    private final Lazy<AirbnbApi> airbnbApi;
    private final Context context;
    private final ConverterFactory converterFactory;
    private final MemoryUtils memoryUtils;

    public LogAirInitializer(Context context2, Lazy<AirbnbApi> airbnbApi2, MemoryUtils memoryUtils2, ConverterFactory converterFactory2) {
        this.context = context2;
        this.airbnbApi = airbnbApi2;
        this.memoryUtils = memoryUtils2;
        this.converterFactory = converterFactory2;
    }

    public void init() {
        LogAir.init(new Builder(this.context).addEventHandler(new JitneyEventHandler(this.context, JITNEY_ENDPOINT, CompressionType.GZIP)).addEventHandler(new JitneyJSONEventHandler(this.context, this.converterFactory, JITNEY_ENDPOINT, CompressionType.GZIP)).addEventHandler(new StandardEventHandler(this.context, this.converterFactory, AIREVENTS_ENDPOINT, CompressionType.GZIP, AirbnbEventLogger.AIREVENTS_TARGET)).userAgent(((AirbnbApi) this.airbnbApi.get()).getUserAgent()).eventsToFetch(this.memoryUtils.hasOomOccurredInLastWeek() ? 15 : 100).build());
    }
}
