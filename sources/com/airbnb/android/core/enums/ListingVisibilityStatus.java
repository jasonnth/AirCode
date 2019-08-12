package com.airbnb.android.core.enums;

import com.airbnb.android.core.C0716R;

public enum ListingVisibilityStatus {
    Listed(C0716R.string.ml_spaces_listed, C0716R.C0717drawable.green_dot, false),
    Snoozed(C0716R.string.ml_spaces_snoozed, C0716R.C0717drawable.yellow_dot, true),
    Unlisted(C0716R.string.ml_spaces_unlisted, C0716R.C0717drawable.red_dot, false);
    
    private final boolean isContentVisible;
    private final int statusIcon;
    private final int title;

    private ListingVisibilityStatus(int title2, int statusIcon2, boolean isContentVisible2) {
        this.title = title2;
        this.statusIcon = statusIcon2;
        this.isContentVisible = isContentVisible2;
    }

    public int getTitle() {
        return this.title;
    }

    public int getStatusIcon() {
        return this.statusIcon;
    }

    public boolean isContentVisible() {
        return this.isContentVisible;
    }
}
