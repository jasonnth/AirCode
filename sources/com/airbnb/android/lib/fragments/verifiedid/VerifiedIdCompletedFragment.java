package com.airbnb.android.lib.fragments.verifiedid;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.events.VerifiedIDStatusEvent;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.VerifiedIdActivity;
import com.airbnb.android.lib.views.CircleBadgeView;
import com.airbnb.android.lib.views.StickyButton;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.imaging.AirImageListener;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.squareup.otto.Bus;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class VerifiedIdCompletedFragment extends Fragment implements VerifiedIdStrapper {
    AirbnbAccountManager mAccountManager;
    Bus mBus;
    private StickyButton mDoneButton;
    private ImageView mListingHeroImage;
    private Reservation mReservation;
    private FrameLayout mReservationContainer;

    public static VerifiedIdCompletedFragment newInstance(Reservation reservation) {
        VerifiedIdCompletedFragment fragment = new VerifiedIdCompletedFragment();
        fragment.setReservation(reservation);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        View view = inflater.inflate(C0880R.layout.fragment_verified_id_completed, container, false);
        CircleBadgeView mCircleBadgeView = (CircleBadgeView) view.findViewById(C0880R.C0882id.circle_badge_view_completed);
        if (hasReservation()) {
            mCircleBadgeView.setVisibility(8);
        } else {
            mCircleBadgeView.initializeAsActiveBadge();
        }
        this.mDoneButton = (StickyButton) view.findViewById(C0880R.C0882id.done_button);
        this.mDoneButton.setTitle(C0880R.string.verified_id_completed_view_profile);
        setupReservationView(view);
        this.mDoneButton.setOnClickListener(VerifiedIdCompletedFragment$$Lambda$1.lambdaFactory$(this));
        if (savedInstanceState == null) {
            VerifiedIdAnalytics.trackCompleteStartView(getVerifiedIdAnalyticsStrap());
            VerifiedIdAnalytics.trackHealth("complete", "start");
        }
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(VerifiedIdCompletedFragment verifiedIdCompletedFragment, View v) {
        verifiedIdCompletedFragment.mAccountManager.getCurrentUser().setVerifiedId(true);
        verifiedIdCompletedFragment.mBus.post(new VerifiedIDStatusEvent(true));
        VerifiedIdAnalytics.trackCompleteStartDone(verifiedIdCompletedFragment.getVerifiedIdAnalyticsStrap());
        VerifiedIdAnalytics.trackHealth("complete", "finish");
        VerifiedIdActivity activity = (VerifiedIdActivity) verifiedIdCompletedFragment.getActivity();
        activity.completeVerifiedIdActivity();
        verifiedIdCompletedFragment.startActivity(UserProfileIntents.intentForCurrentUser(activity));
    }

    public void setReservation(Reservation reservation) {
        this.mReservation = reservation;
    }

    private boolean hasReservation() {
        return this.mReservation != null;
    }

    private void setupReservationView(View view) {
        this.mReservationContainer = (FrameLayout) view.findViewById(C0880R.C0882id.reservation_container);
        this.mListingHeroImage = (ImageView) view.findViewById(C0880R.C0882id.listing_hero_image);
        TextView mListingNameTextView = (TextView) view.findViewById(C0880R.C0882id.listing_name);
        TextView mReservationDescriptionTextView = (TextView) view.findViewById(C0880R.C0882id.reservation_description);
        TextView mCompletionDetailsTextView = (TextView) view.findViewById(C0880R.C0882id.completion_details);
        if (hasReservation()) {
            this.mReservationContainer.setVisibility(0);
            LayoutParams params = new LayoutParams(-1, -2);
            params.gravity = 48;
            view.setLayoutParams(params);
            mListingNameTextView.setText(this.mReservation.getListing().getName());
            mReservationDescriptionTextView.setText(getReservationDetailsText());
            mCompletionDetailsTextView.setText(getString(C0880R.string.verified_id_completed_reservation_details, (this.mReservation.getHost() != null ? this.mReservation.getHost() : this.mReservation.getListing().getPrimaryHost()).getFirstName()));
            AirImageView.getImage(getActivity(), this.mReservation.getListing().getPictureUrl(), new AirImageListener() {
                public void onResponse(Bitmap response, boolean isImmediate) {
                    VerifiedIdCompletedFragment.this.bitmapBlurComplete(response);
                }

                public void onErrorResponse(Exception exception) {
                }
            });
            this.mDoneButton.setTitle(C0880R.string.verified_id_completed_back_to_reservation);
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    private String getReservationDetailsText() {
        SimpleDateFormat format = new SimpleDateFormat(getActivity().getString(C0880R.string.date_name_format));
        int guestsCount = this.mReservation.getGuestCount();
        String numGuests = getActivity().getResources().getQuantityString(C0880R.plurals.x_guests, guestsCount, new Object[]{Integer.valueOf(guestsCount)});
        String checkInDate = this.mReservation.getStartDate().formatDate((DateFormat) format);
        String checkOutDate = this.mReservation.getEndDate().formatDate((DateFormat) format);
        return getString(C0880R.string.verified_id_reservation_details, checkInDate, checkOutDate, numGuests);
    }

    public void bitmapBlurComplete(Bitmap result) {
        this.mListingHeroImage.setImageBitmap(result);
        int gradientHeight = Math.round(((float) ViewUtils.getScreenSize(getActivity()).x) * (((float) result.getHeight()) / ((float) result.getWidth())));
        View shade = new View(getActivity());
        shade.setBackground(getResources().getDrawable(C0880R.C0881drawable.host_home_hero_gradient));
        shade.setLayoutParams(new ViewGroup.LayoutParams(this.mListingHeroImage.getWidth(), gradientHeight));
        this.mReservationContainer.addView(shade, this.mReservationContainer.indexOfChild(this.mListingHeroImage) + 1);
        ((FrameLayout.LayoutParams) this.mListingHeroImage.getLayoutParams()).height = gradientHeight;
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((VerifiedIdActivity) getActivity()).getReservationIdStringForAnalytics());
    }
}
