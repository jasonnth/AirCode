package com.airbnb.android.core.enums;

import android.content.Context;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0716R;

public enum InstantBookAdvanceNotice {
    SameDay(getTitle(2), 2),
    One(getTitle(24), 24),
    Two(getTitle(48), 48),
    Three(getTitle(72), 72),
    Seven(getTitle(168), 168);
    
    public static final InstantBookAdvanceNotice DEFAULT = null;
    public final int serverDescKey;
    public final int titleId;

    static {
        DEFAULT = SameDay;
    }

    private InstantBookAdvanceNotice(int titleId2, int serverDescKey2) {
        this.titleId = titleId2;
        this.serverDescKey = serverDescKey2;
    }

    public static InstantBookAdvanceNotice getType(int index) {
        InstantBookAdvanceNotice[] values = values();
        if (index < 0 || index >= values.length) {
            return null;
        }
        return values[index];
    }

    public static InstantBookAdvanceNotice getTypeFromKey(int advanceNoticeKey) {
        InstantBookAdvanceNotice[] values;
        if (advanceNoticeKey < 24) {
            return SameDay;
        }
        for (InstantBookAdvanceNotice advanceNotice : values()) {
            if (advanceNotice.serverDescKey == advanceNoticeKey) {
                return advanceNotice;
            }
        }
        BugsnagWrapper.notify(new Throwable("Unknown key in InstantBookAdvanceNotice: " + advanceNoticeKey));
        return DEFAULT;
    }

    public static int getTitle(int leadHours) {
        switch (leadHours) {
            case 24:
                return C0716R.string.calendar_advance_notice_one;
            case 48:
                return C0716R.string.calendar_advance_notice_two;
            case 72:
                return C0716R.string.calendar_advance_notice_three;
            case 168:
                return C0716R.string.calendar_advance_notice_seven;
            default:
                return C0716R.string.calendar_advance_notice_same_day;
        }
    }

    public String getStatusMessage(Context context) {
        int days = this.serverDescKey / 24;
        if (days == 0) {
            return context.getString(C0716R.string.advance_notice_status_same_day);
        }
        return context.getResources().getQuantityString(C0716R.plurals.advance_notice_status, days, new Object[]{Integer.valueOf(days)});
    }

    public String getHowManyDays(Context context) {
        int days = this.serverDescKey / 24;
        if (days == 0) {
            return context.getString(C0716R.string.f1047x2c8971ce);
        }
        return context.getResources().getQuantityString(C0716R.plurals.x_days, days, new Object[]{Integer.valueOf(days)});
    }
}
