package com.airbnb.android.lib.activities;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.airbnb.android.core.intents.PostBookingActivityIntents;
import com.airbnb.android.core.models.Reservation;

final /* synthetic */ class DebugMenuActivity$$Lambda$9 implements OnClickListener {
    private final DebugMenuActivity arg$1;
    private final Reservation[] arg$2;
    private final int[] arg$3;

    private DebugMenuActivity$$Lambda$9(DebugMenuActivity debugMenuActivity, Reservation[] reservationArr, int[] iArr) {
        this.arg$1 = debugMenuActivity;
        this.arg$2 = reservationArr;
        this.arg$3 = iArr;
    }

    public static OnClickListener lambdaFactory$(DebugMenuActivity debugMenuActivity, Reservation[] reservationArr, int[] iArr) {
        return new DebugMenuActivity$$Lambda$9(debugMenuActivity, reservationArr, iArr);
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.arg$1.startActivity(PostBookingActivityIntents.intentForReservation(this.arg$1, this.arg$2[this.arg$3[0]]));
    }
}
