package com.facebook.accountkit.p029ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

/* renamed from: com.facebook.accountkit.ui.CountryCodeSpinner */
public final class CountryCodeSpinner extends Spinner {
    private OnSpinnerEventsListener listener;
    private boolean openStarted = false;

    /* renamed from: com.facebook.accountkit.ui.CountryCodeSpinner$OnSpinnerEventsListener */
    interface OnSpinnerEventsListener {
        void onSpinnerClosed();

        void onSpinnerOpened();
    }

    public CountryCodeSpinner(Context context) {
        super(context);
    }

    public CountryCodeSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountryCodeSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean performClick() {
        this.openStarted = true;
        if (this.listener != null) {
            this.listener.onSpinnerOpened();
        }
        return super.performClick();
    }

    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (this.openStarted && hasWindowFocus) {
            performClosedEvent();
        }
    }

    /* access modifiers changed from: 0000 */
    public void setOnSpinnerEventsListener(OnSpinnerEventsListener onSpinnerEventsListener) {
        this.listener = onSpinnerEventsListener;
    }

    private void performClosedEvent() {
        this.openStarted = false;
        if (this.listener != null) {
            this.listener.onSpinnerClosed();
        }
    }
}
