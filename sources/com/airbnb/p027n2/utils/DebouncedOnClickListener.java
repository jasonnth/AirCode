package com.airbnb.p027n2.utils;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.utils.DebouncedOnClickListener */
public abstract class DebouncedOnClickListener implements OnClickListener {
    private static final long DEFAULT_DEBOUNCE_TIME_MS = 2000;
    private final long debounceTimeMs;
    private long lastClickTime;

    public abstract void onClickDebounced(View view);

    public DebouncedOnClickListener() {
        this(DEFAULT_DEBOUNCE_TIME_MS);
    }

    public DebouncedOnClickListener(long debounceTimeMs2) {
        this.debounceTimeMs = debounceTimeMs2;
    }

    public final void onClick(View v) {
        Log.d("wishlist", "on heart clicked");
        long now = System.currentTimeMillis();
        if (now - this.lastClickTime >= this.debounceTimeMs) {
            this.lastClickTime = now;
            onClickDebounced(v);
        }
    }
}
