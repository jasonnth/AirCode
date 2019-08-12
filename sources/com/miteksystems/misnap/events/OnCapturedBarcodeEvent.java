package com.miteksystems.misnap.events;

import android.content.Intent;

public class OnCapturedBarcodeEvent {
    public final Intent returnIntent;

    public OnCapturedBarcodeEvent(Intent returnIntent2) {
        this.returnIntent = returnIntent2;
    }
}
