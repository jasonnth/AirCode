package com.airbnb.android.lib.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.requests.ReservationRequest;
import com.airbnb.android.core.requests.ReservationRequest.Format;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.lib.cancellation.host.HostCancellationActivity;
import p032rx.Observer;

public class HostCancellationIntentActivity extends AirActivity {
    final RequestListener<ReservationResponse> reservationListener = new C0699RL().onResponse(HostCancellationIntentActivity$$Lambda$1.lambdaFactory$(this)).onError(HostCancellationIntentActivity$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(HostCancellationIntentActivity hostCancellationIntentActivity, ReservationResponse response) {
        hostCancellationIntentActivity.startActivity(HostCancellationActivity.intentForReservation(hostCancellationIntentActivity, response.reservation));
        hostCancellationIntentActivity.finish();
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (DeepLinkUtils.isDeepLink(intent)) {
            String confirmationCode = DeepLinkUtils.getParamAsString(getIntent(), "confirmation_code");
            long reservationId = DeepLinkUtils.getParamAsId(getIntent(), "reservation_id");
            if (reservationId != -1) {
                ReservationRequest.forReservationId(reservationId, Format.Host).withListener((Observer) this.reservationListener).skipCache().execute(this.requestManager);
            } else if (!TextUtils.isEmpty(confirmationCode)) {
                ReservationRequest.forConfirmationCode(confirmationCode, Format.Host).withListener((Observer) this.reservationListener).skipCache().execute(this.requestManager);
            } else {
                DeepLinkUtils.notifyUnhandledDeepLink(intent);
                finish();
            }
        } else {
            DeepLinkUtils.notifyUnhandledDeepLink(intent);
            finish();
        }
    }
}
