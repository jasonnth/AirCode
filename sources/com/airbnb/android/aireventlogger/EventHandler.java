package com.airbnb.android.aireventlogger;

interface EventHandler {
    void deleteEvents(int i, int i2);

    PendingEvents getPendingEvents(int i);

    <T> void saveEvent(AirEvent<T> airEvent);

    <T> boolean supports(AirEvent<T> airEvent);
}
