package com.airbnb.android.lib.services;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.requests.GuestReservationsRequest;
import com.airbnb.android.core.requests.ReservationRequest;
import com.airbnb.android.core.requests.ReservationRequest.Format;
import com.airbnb.android.core.requests.SharedReservationRequest;
import com.airbnb.android.core.responses.GuestReservationsResponse;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.services.TripsReservationsSyncServiceIntents;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.MapUtil;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.StaticMapInfo;
import com.google.android.gms.maps.model.LatLng;
import java.util.List;
import p032rx.Observer;

public class TripsReservationsSyncService extends IntentService {
    private static final String KEY_LAST_OFFLINE_FETCH = "fetch_offline_trips_last_fetch";
    private static final String TAG = TripsReservationsSyncService.class.getSimpleName();
    AirbnbApi airbnbApi;
    AirbnbAccountManager mAccountManager;
    AirbnbPreferences mPreferences;
    private final NonResubscribableRequestListener<ReservationResponse> reservationsListener = new C0699RL().onResponse(TripsReservationsSyncService$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    private int syncOptions;
    private final NonResubscribableRequestListener<GuestReservationsResponse> tripsListener = new C0699RL().onResponse(TripsReservationsSyncService$$Lambda$1.lambdaFactory$(this)).buildWithoutResubscription();

    public TripsReservationsSyncService() {
        super(TAG);
    }

    /* access modifiers changed from: protected */
    public void onHandleIntent(Intent intent) {
        if (intent == null) {
            BugsnagWrapper.notify((Throwable) new NullPointerException("Intent is null"));
            return;
        }
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        if (this.mAccountManager.isCurrentUserAuthorized()) {
            this.syncOptions = intent.getIntExtra(TripsReservationsSyncServiceIntents.KEY_SYNC_OPTIONS, 1);
            if (this.syncOptions == 1 || this.syncOptions == 2) {
                fetchTripsData();
            }
        }
    }

    private void fetchTripsData() {
        GuestReservationsRequest.forUpcoming(this.airbnbApi, 0, this.mAccountManager.getCurrentUserId(), true).withListener((Observer) this.tripsListener).skipCache().execute(NetworkUtil.singleFireExecutor());
    }

    /* access modifiers changed from: private */
    public void fetchReservationsList(List<Reservation> reservations) {
        if (this.syncOptions != 2) {
            if (System.currentTimeMillis() - this.mPreferences.getSharedPreferences().getLong(KEY_LAST_OFFLINE_FETCH, 0) > 86400000) {
                if (reservations != null && !reservations.isEmpty()) {
                    for (Reservation reservation : reservations) {
                        fetchReservation(reservation);
                    }
                }
                this.mPreferences.getSharedPreferences().edit().putLong(KEY_LAST_OFFLINE_FETCH, System.currentTimeMillis()).apply();
            }
        }
    }

    private void fetchReservation(Reservation reservation) {
        if (reservation.isSharedItinerary()) {
            SharedReservationRequest.forSharedItinerary(reservation.getConfirmationCode()).execute(NetworkUtil.singleFireExecutor());
        } else {
            ReservationRequest.forConfirmationCode(reservation.getConfirmationCode(), Format.Guest).withListener((Observer) this.reservationsListener).skipCache().execute(NetworkUtil.singleFireExecutor());
        }
    }

    /* access modifiers changed from: private */
    public void fetchReservationDetails(Reservation reservation) {
        if (reservation != null && reservation.getListing() != null) {
            Listing listing = reservation.getListing();
            fetchListingCoverImage(listing);
            fetchListingStaticMap(listing);
        }
    }

    private void fetchListingStaticMap(Listing listing) {
        StaticMapInfo mapInfo = new StaticMapInfo();
        mapInfo.setup(AppLaunchUtils.isUserInChina(), false);
        mapInfo.addMarkerToMap(listing.getLatitude(), listing.getLongitude());
        LatLng offsetLatLng = MapUtil.getOffsetLatLng(listing);
        mapInfo.centerMap(offsetLatLng.latitude, offsetLatLng.longitude, 12);
        AirImageView.getImageBackground(this, mapInfo.getStaticMapUrl(getApplicationContext()));
    }

    private void fetchListingCoverImage(Listing listing) {
        String listingImageUrl = listing.getPictureUrl();
        if (!TextUtils.isEmpty(listingImageUrl)) {
            AirImageView.getImageBackground(this, listingImageUrl);
        }
    }
}
