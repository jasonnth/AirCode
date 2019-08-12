package com.airbnb.android.lib.fragments.reservationresponse;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.enums.DeclineReason;
import com.airbnb.android.lib.activities.ReservationResponseActivity;

/* renamed from: com.airbnb.android.lib.fragments.reservationresponse.ReservationDeclineLandingFragment$DeclineReasonsAdapter$$Lambda$1 */
final /* synthetic */ class C6975x6355aba3 implements OnClickListener {
    private final ReservationResponseActivity arg$1;
    private final DeclineReason arg$2;

    private C6975x6355aba3(ReservationResponseActivity reservationResponseActivity, DeclineReason declineReason) {
        this.arg$1 = reservationResponseActivity;
        this.arg$2 = declineReason;
    }

    public static OnClickListener lambdaFactory$(ReservationResponseActivity reservationResponseActivity, DeclineReason declineReason) {
        return new C6975x6355aba3(reservationResponseActivity, declineReason);
    }

    public void onClick(View view) {
        this.arg$1.getNavigator().onDeclineReasonSelected(this.arg$2);
    }
}
