package com.airbnb.android.lib.fragments.verifiedid;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.VerifiedIdActivity;
import com.airbnb.android.lib.views.StickyButton;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.primitives.imaging.AirImageListener;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class WelcomeScreenFragment extends Fragment implements VerifiedIdStrapper {
    private static final String RESERVATION_EXTRA = "reservation";
    private static final String START_COMPLETE_PROFILE_EXTRA = "start_complete_profile";
    private ImageView mListingHeroImage;
    private Reservation mReservation;
    private FrameLayout mReservationContainer;
    private boolean mStartCompleteProfile;
    private StickyButton mVerifyMeButton;

    public static WelcomeScreenFragment newInstance(boolean startCompleteProfile, Reservation reservation) {
        WelcomeScreenFragment fragment = new WelcomeScreenFragment();
        Bundle arguments = new Bundle();
        arguments.putBoolean(START_COMPLETE_PROFILE_EXTRA, startCompleteProfile);
        arguments.putParcelable("reservation", reservation);
        fragment.setArguments(arguments);
        return fragment;
    }

    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        View view = inflater.inflate(C0880R.layout.fragment_verified_id_welcome, container, false);
        this.mStartCompleteProfile = getArguments().getBoolean(START_COMPLETE_PROFILE_EXTRA);
        this.mReservation = (Reservation) getArguments().getParcelable("reservation");
        if (this.mReservation == null) {
            ((LayoutParams) ((LinearLayout) view.findViewById(C0880R.C0882id.fragment_content)).getLayoutParams()).gravity = 16;
        }
        setupReservationView(view);
        ((TextView) view.findViewById(C0880R.C0882id.learn_more_link)).setOnClickListener(WelcomeScreenFragment$$Lambda$1.lambdaFactory$(this));
        this.mVerifyMeButton = (StickyButton) view.findViewById(C0880R.C0882id.welcome_button);
        this.mVerifyMeButton.setTitle(C0880R.string.verified_id_verify_button);
        this.mVerifyMeButton.setOnClickListener(WelcomeScreenFragment$$Lambda$2.lambdaFactory$(this));
        if (savedInstanceState == null) {
            VerifiedIdAnalytics.trackWelcomeStartView();
            VerifiedIdAnalytics.trackHealth("welcome", "start");
        }
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$1(WelcomeScreenFragment welcomeScreenFragment, View v) {
        VerifiedIdActivity activity = (VerifiedIdActivity) welcomeScreenFragment.getActivity();
        VerifiedIdAnalytics.trackWelcomeStartVerifyMe(welcomeScreenFragment.getVerifiedIdAnalyticsStrap());
        VerifiedIdAnalytics.trackHealth("welcome", "finish");
        welcomeScreenFragment.mVerifyMeButton.setEnabled(false);
        activity.showNextStep();
    }

    public void onResume() {
        super.onResume();
        this.mVerifyMeButton.setEnabled(true);
        if (this.mStartCompleteProfile) {
            this.mStartCompleteProfile = false;
            this.mVerifyMeButton.setEnabled(false);
            ((VerifiedIdActivity) getActivity()).showNextStep();
        }
    }

    private void setupReservationView(View view) {
        this.mReservationContainer = (FrameLayout) view.findViewById(C0880R.C0882id.reservation_container);
        this.mListingHeroImage = (ImageView) view.findViewById(C0880R.C0882id.listing_hero_image);
        TextView mListingNameTextView = (TextView) view.findViewById(C0880R.C0882id.listing_name);
        TextView mReservationDescriptionTextView = (TextView) view.findViewById(C0880R.C0882id.reservation_description);
        TextView mWelcomePromptHeaderTextView = (TextView) view.findViewById(C0880R.C0882id.welcome_prompt_header);
        if (this.mReservation != null) {
            this.mReservationContainer.setVisibility(0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
            params.gravity = 48;
            view.setLayoutParams(params);
            mListingNameTextView.setText(this.mReservation.getListing().getName());
            mReservationDescriptionTextView.setText(getReservationDetailsText());
            mWelcomePromptHeaderTextView.setText(getActivity().getString(C0880R.string.verified_id_welcome_header_reservation));
            AirImageView.getImage(getActivity(), this.mReservation.getListing().getPictureUrl(), new AirImageListener() {
                public void onResponse(Bitmap response, boolean isImmediate) {
                    WelcomeScreenFragment.this.bitmapBlurComplete(response);
                }

                public void onErrorResponse(Exception exception) {
                }
            });
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    private String getReservationDetailsText() {
        SimpleDateFormat format = new SimpleDateFormat(getActivity().getString(C0880R.string.date_name_format));
        int guestsCount = this.mReservation.getGuestCount();
        String numGuests = getActivity().getResources().getQuantityString(C0880R.plurals.x_guests, guestsCount, new Object[]{Integer.valueOf(guestsCount)});
        String checkInDate = this.mReservation.getStartDate().formatDate((DateFormat) format);
        String checkOutDate = this.mReservation.getEndDate().formatDate((DateFormat) format);
        return getActivity().getResources().getString(C0880R.string.verified_id_reservation_details, new Object[]{checkInDate, checkOutDate, numGuests});
    }

    public void bitmapBlurComplete(Bitmap result) {
        if (getActivity() != null) {
            this.mListingHeroImage.setImageBitmap(result);
            int gradientHeight = Math.round(((float) this.mListingHeroImage.getWidth()) * (((float) result.getHeight()) / ((float) result.getWidth())));
            View shade = new View(getActivity());
            shade.setBackground(getResources().getDrawable(C0880R.C0881drawable.host_home_hero_gradient));
            shade.setLayoutParams(new ViewGroup.LayoutParams(this.mListingHeroImage.getWidth(), gradientHeight));
            this.mReservationContainer.addView(shade, this.mReservationContainer.indexOfChild(this.mListingHeroImage) + 1);
            ((LayoutParams) this.mListingHeroImage.getLayoutParams()).height = gradientHeight;
        }
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((VerifiedIdActivity) getActivity()).getReservationIdStringForAnalytics());
    }
}
