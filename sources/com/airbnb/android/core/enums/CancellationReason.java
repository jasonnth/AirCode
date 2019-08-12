package com.airbnb.android.core.enums;

import com.airbnb.android.core.C0716R;
import com.google.common.collect.ImmutableList;
import java.util.List;

public enum CancellationReason {
    Unnecessary(101, C0716R.string.cancel_reason_unnecessary),
    Accident(102, C0716R.string.cancel_reason_accident),
    Emergency(103, C0716R.string.cancel_reason_emergency),
    Asked(104, C0716R.string.cancel_reason_asked),
    Uncomfortable(105, C0716R.string.cancel_reason_uncomfortable),
    Dislike(106, C0716R.string.cancel_reason_dislike),
    Dates(107, C0716R.string.cancel_reason_dates),
    Other(199, C0716R.string.cancel_reason_other);
    
    private final int reasonId;
    private final int reasonStr;

    private CancellationReason(int reasonId2, int reasonStr2) {
        this.reasonId = reasonId2;
        this.reasonStr = reasonStr2;
    }

    public int getReasonId() {
        return this.reasonId;
    }

    public int getReasonStr() {
        return this.reasonStr;
    }

    public static List<CancellationReason> getGuestCancellationReasons() {
        return ImmutableList.m1291of(Unnecessary, Dates, Accident, Emergency, Asked, Uncomfortable, Other);
    }
}
