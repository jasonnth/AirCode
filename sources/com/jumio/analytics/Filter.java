package com.jumio.analytics;

import android.annotation.SuppressLint;
import java.util.ArrayList;
import java.util.List;

public class Filter {
    private final List<Integer> mBlockedEvents = new ArrayList();

    public Filter disable(int eventType) {
        this.mBlockedEvents.add(Integer.valueOf(eventType));
        return this;
    }

    @SuppressLint({"UseValueOf"})
    public Filter enable(int eventType) {
        this.mBlockedEvents.remove(Integer.valueOf(eventType));
        return this;
    }

    public boolean contains(int eventType) {
        return this.mBlockedEvents.contains(Integer.valueOf(eventType));
    }
}
