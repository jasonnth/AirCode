package com.miteksystems.misnap.events;

import android.content.Intent;

public class OnCapturedFrameEvent {
    public final Intent returnIntent;

    public OnCapturedFrameEvent(Intent returnIntent2) {
        this.returnIntent = returnIntent2;
    }
}
