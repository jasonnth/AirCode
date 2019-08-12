package com.airbnb.android.p011p3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.GuestReservationsRequest;
import com.airbnb.android.core.responses.GuestReservationsResponse;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirTextView;
import icepick.State;
import p032rx.Observer;

/* renamed from: com.airbnb.android.p3.AccountVerificationContactHostFragment */
public class AccountVerificationContactHostFragment extends AirFragment {
    private static final String ARG_LISTING_ID = "arg_listing_id";
    @State
    boolean hasBookings;
    @State
    long listingId;
    @BindView
    AirTextView subTitleDescTextView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<GuestReservationsResponse> tripsRequestListener = new C0699RL().onResponse(AccountVerificationContactHostFragment$$Lambda$1.lambdaFactory$(this)).onError(AccountVerificationContactHostFragment$$Lambda$2.lambdaFactory$(this)).build();

    /* renamed from: com.airbnb.android.p3.AccountVerificationContactHostFragment$ProvideIdClickListener */
    public interface ProvideIdClickListener {
        void onProvideIdClick();
    }

    static /* synthetic */ void lambda$new$0(AccountVerificationContactHostFragment accountVerificationContactHostFragment, GuestReservationsResponse response) {
        if (response.reservations != null && !response.reservations.isEmpty()) {
            accountVerificationContactHostFragment.hasBookings = true;
            accountVerificationContactHostFragment.subTitleDescTextView.setText(C7532R.string.verifications_be_ready_to_book_again_desc);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.listingId = getArguments().getLong("arg_listing_id");
        }
        View view = inflater.inflate(C7532R.layout.fragment_account_verification_contact_host, container, false);
        bindViews(view);
        if (this.hasBookings) {
            this.subTitleDescTextView.setText(C7532R.string.verifications_be_ready_to_book_again_desc);
        } else {
            fetchTrips();
        }
        return view;
    }

    private void fetchTrips() {
        if (!this.requestManager.hasRequest((BaseRequestListener<T>) this.tripsRequestListener, GuestReservationsRequest.class)) {
            GuestReservationsRequest.forUpcomingAndPast(this.mAirbnbApi, 0, 1, this.mAccountManager.getCurrentUserId()).withListener((Observer) this.tripsRequestListener).doubleResponse(true).execute(this.requestManager);
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.toolbar.setTheme(3);
        this.toolbar.setNavigationIcon(2);
        setToolbar(this.toolbar);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.VerificationContactHostStart;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11638kv("id_listing", this.listingId);
    }

    @OnClick
    public void onProvideIdClick() {
        ((ProvideIdClickListener) getActivity()).onProvideIdClick();
        AccountVerificationAnalytics.trackButtonClick(getNavigationTrackingTag(), "provide_id");
    }
}
