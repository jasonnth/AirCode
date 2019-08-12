package com.airbnb.android.listing.logging;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.android.core.models.C6120RoomType;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSWmpwClickContinueEvent;
import com.airbnb.jitney.event.logging.LYS.p129v1.LYSWmpwSelectCityEvent.Builder;
import com.airbnb.jitney.event.logging.LYS.p130v2.LYSWmpwSelectGuestNumberEvent;
import com.airbnb.jitney.event.logging.LYS.p130v2.LYSWmpwSelectPropertyTypeEvent;

public class WhatsMyPlaceWorthLogger extends BaseLogger {
    public WhatsMyPlaceWorthLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void selectCityEvent(String city) {
        publish(new Builder(context(), city));
    }

    public void selectCapacityEvent(int capacity) {
        publish(new LYSWmpwSelectGuestNumberEvent.Builder(context(), Integer.toString(capacity)));
    }

    public void selectRoomType(C6120RoomType roomType) {
        publish(new LYSWmpwSelectPropertyTypeEvent.Builder(context(), roomType.key));
    }

    public void logContinueEvent(boolean listingCreated) {
        publish(new LYSWmpwClickContinueEvent.Builder(context(), "", Boolean.valueOf(listingCreated)));
    }
}
