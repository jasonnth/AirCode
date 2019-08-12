package com.jumio.commons.enums;

import com.airbnb.android.core.models.MaxDaysNoticeSetting;

public enum ScreenAngle {
    PORTRAIT(0),
    LANDSCAPE(90),
    INVERTED_PORTRAIT(180),
    INVERTED_LANDSCAPE(MaxDaysNoticeSetting.MAX_DAYS_NOTICE_9_MONTHS);
    
    private final int angle;

    private ScreenAngle(int value) {
        this.angle = value;
    }

    public int getValue() {
        return this.angle;
    }
}
