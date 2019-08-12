package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.deeplinks.WebLink;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.fragments.HeroMarqueeFragment;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.requests.ReservationRequest;
import com.airbnb.android.core.requests.ReservationRequest.Format;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.host.HostReservationIntentFactory;
import p032rx.Observer;

public class ReservationObjectDeepLinkActivity extends AirActivity {
    private static final String EXTRA_NOTIFICATION_BODY = "message";
    private static final String EXTRA_NOTIFICATION_BUTTON_TEXT = "button_text";
    private static final String EXTRA_NOTIFICATION_TITLE = "title";
    private static final int REQUEST_CODE_NOTIFICATION = 283;
    private Reservation reservation;
    final RequestListener<ReservationResponse> savedMessagesRequestListener = new C0699RL().onResponse(ReservationObjectDeepLinkActivity$$Lambda$1.lambdaFactory$(this)).onError(ReservationObjectDeepLinkActivity$$Lambda$2.lambdaFactory$(this)).build();

    @WebLink({"home/itinerary", "reservation/itinerary", "reservations/guest", "reservation/approve"})
    public static Intent getIntent(Context context, Bundle bundle) {
        String code = bundle.getString("code");
        if (code == null || code.length() < 6) {
            return HomeActivityIntents.intentForTrips(context);
        }
        if (bundle.getString("trip_token") != null) {
            return HomeActivityIntents.intentForTrips(context);
        }
        return new Intent(context, ReservationObjectDeepLinkActivity.class).putExtra("confirmation_code", code);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_loading);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            ReservationRequest request = null;
            if (DeepLinkUtils.isDeepLink(intent)) {
                request = createDeepLinkRequest(intent);
            } else if (intent.hasExtra("confirmation_code")) {
                request = createRequestForConfirmationCode(intent.getStringExtra("confirmation_code"), this.sharedPrefsHelper.isGuestMode() ? Format.Guest : Format.Host);
            } else if (intent.hasExtra("reservation_id")) {
                request = createRequestForReservationId(intent.getLongExtra("reservation_id", -1));
            }
            if (request == null) {
                Toast.makeText(this, C0880R.string.error, 0).show();
                startActivity(HomeActivityIntents.intentForDefaultTab(this));
                finish();
                return;
            }
            request.withListener((Observer) this.savedMessagesRequestListener).execute(this.requestManager);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_NOTIFICATION && resultCode == -1) {
            startReservationActivity(this.reservation);
        }
    }

    private ReservationRequest createDeepLinkRequest(Intent intent) {
        String confirmationCode = DeepLinkUtils.getParamAsString(intent, "code", "reservation_confirmation_code");
        if (!TextUtils.isEmpty(confirmationCode)) {
            return createRequestForConfirmationCode(confirmationCode, this.sharedPrefsHelper.isGuestMode() ? Format.Guest : Format.Host);
        }
        long reservationId = DeepLinkUtils.getParamAsId(getIntent(), "id");
        if (reservationId != -1) {
            return createRequestForReservationId(reservationId);
        }
        DeepLinkUtils.notifyUnhandledDeepLink(intent);
        return null;
    }

    private static ReservationRequest createRequestForConfirmationCode(String confirmationCode, Format appMode) {
        return ReservationRequest.forConfirmationCode(confirmationCode, appMode);
    }

    private static ReservationRequest createRequestForReservationId(long reservationId) {
        return ReservationRequest.forReservationId(reservationId, Format.Guest);
    }

    /* access modifiers changed from: private */
    public void handleReservation(Reservation reservation2) {
        this.reservation = reservation2;
        if (shouldShowNotificationDialog()) {
            showNotificationDialog();
        } else {
            startReservationActivity(reservation2);
        }
    }

    private void startReservationActivity(Reservation reservation2) {
        Intent intent;
        if (reservation2.isUserHost(this.accountManager.getCurrentUser())) {
            intent = HostReservationIntentFactory.forConfirmationCode(this, reservation2.getConfirmationCode(), ROLaunchSource.WebIntent);
        } else {
            intent = ReactNativeIntents.intentForItineraryCheckinCard(this, reservation2.getConfirmationCode());
        }
        startActivity(intent);
    }

    private boolean shouldShowNotificationDialog() {
        Intent intent = getIntent();
        return intent.hasExtra("title") && intent.hasExtra(EXTRA_NOTIFICATION_BUTTON_TEXT);
    }

    private void showNotificationDialog() {
        Intent intent = getIntent();
        String notificationTitle = intent.getStringExtra("title");
        String notificationBody = intent.getStringExtra("message");
        String notificationButtonText = intent.getStringExtra(EXTRA_NOTIFICATION_BUTTON_TEXT);
        if (notificationTitle != null && notificationButtonText != null) {
            HeroMarqueeFragment.builder().withTitle(notificationTitle).withBodyText(notificationBody).withFirstButtonText(notificationButtonText).withRequestCode(REQUEST_CODE_NOTIFICATION).build().show(getSupportFragmentManager(), (String) null);
        }
    }

    /* access modifiers changed from: private */
    public void handleFetchError(NetworkException e) {
        NetworkUtil.toastNetworkError((Context) this, e);
        finish();
    }
}
