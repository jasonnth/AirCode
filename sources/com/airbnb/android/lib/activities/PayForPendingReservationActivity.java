package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.core.events.AlertsChangedEvent;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.Reservation;
import icepick.State;

public class PayForPendingReservationActivity extends SubmitPaymentActivity {
    private static final String EXTRA_CONFIRMATION_CODE = "extra_confirmation_code";
    @State
    String confirmationCode;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.confirmationCode = getIntent().getStringExtra("extra_confirmation_code");
    }

    public static Intent intentForPayment(Context context, long paymentId, Reservation reservation) {
        return forPaymentId(context, paymentId).setClass(context, PayForPendingReservationActivity.class).toIntent().putExtra("extra_confirmation_code", validateReservationHasConfirmationCode(reservation));
    }

    public static Intent intentForReservation(Context context, Reservation reservation) {
        String confirmationCode2 = validateReservationHasConfirmationCode(reservation);
        return forConfirmationCode(context, confirmationCode2).setClass(context, PayForPendingReservationActivity.class).toIntent().putExtra("extra_confirmation_code", confirmationCode2);
    }

    private static String validateReservationHasConfirmationCode(Reservation reservation) {
        String confirmationCode2 = reservation.getConfirmationCode();
        if (!TextUtils.isEmpty(confirmationCode2)) {
            return confirmationCode2;
        }
        throw new IllegalStateException("Null or empty confirmationCode");
    }

    /* access modifiers changed from: protected */
    public void onPaymentSuccess() {
        this.bus.post(new AlertsChangedEvent());
        if (getCallingActivity() == null) {
            startActivity(ReactNativeIntents.intentForItineraryCheckinCard(this, this.confirmationCode));
        } else {
            setResult(-1);
        }
        finish();
    }
}
