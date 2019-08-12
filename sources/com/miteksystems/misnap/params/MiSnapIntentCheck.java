package com.miteksystems.misnap.params;

import android.content.Intent;

public class MiSnapIntentCheck {
    public static boolean isDangerous(Intent intent) {
        return intent == null || intent.getExtras() == null || intent.getStringExtra(MiSnapAPI.JOB_SETTINGS) == null || intent.getStringExtra(MiSnapAPI.JOB_SETTINGS).isEmpty();
    }
}
