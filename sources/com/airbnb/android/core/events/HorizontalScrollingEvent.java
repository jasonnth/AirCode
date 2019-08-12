package com.airbnb.android.core.events;

public class HorizontalScrollingEvent {
    public final boolean scrolling;

    public HorizontalScrollingEvent(boolean scrollingHorizontally) {
        this.scrolling = scrollingHorizontally;
    }
}
