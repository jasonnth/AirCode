package com.airbnb.android.lib.fragments.managelisting.handlers;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.fragments.ReservationCancellationWithUserInputFragment.InputReason;
import com.airbnb.android.lib.fragments.ReservationCancellationWithUserInputFragment.ReservationCancellationWithUserInputController;

final /* synthetic */ class ReservationCancellationConfirmationInputAdapter$$Lambda$1 implements OnClickListener {
    private final ReservationCancellationWithUserInputController arg$1;
    private final String arg$2;

    private ReservationCancellationConfirmationInputAdapter$$Lambda$1(ReservationCancellationWithUserInputController reservationCancellationWithUserInputController, String str) {
        this.arg$1 = reservationCancellationWithUserInputController;
        this.arg$2 = str;
    }

    public static OnClickListener lambdaFactory$(ReservationCancellationWithUserInputController reservationCancellationWithUserInputController, String str) {
        return new ReservationCancellationConfirmationInputAdapter$$Lambda$1(reservationCancellationWithUserInputController, str);
    }

    public void onClick(View view) {
        this.arg$1.editUserInputClicked(InputReason.Confirmation, this.arg$2);
    }
}
