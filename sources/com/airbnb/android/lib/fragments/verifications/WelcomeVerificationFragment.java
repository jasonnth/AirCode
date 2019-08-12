package com.airbnb.android.lib.fragments.verifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.ImageUtils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.VerificationsActivity;
import com.airbnb.android.lib.enums.VerificationFlowToLayoutId;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import icepick.State;

public class WelcomeVerificationFragment extends VerificationsFragment {
    private static final String ARG_LISTING = "arg_listing";
    private static final String ARG_VERIFICATION_FLOW = "arg_verification_flow";
    @BindView
    ImageView backgroundImageView;
    @BindView
    TextView bookingHeaderTextView;
    @BindView
    View contentContainer;
    @BindView
    ImageView gradientImage;
    @BindView
    HaloImageView guestHaloImageView;
    @BindView
    HaloImageView hostHaloImageView;
    private final OnLayoutChangeListener listener = new OnLayoutChangeListener() {
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            int contentHeight = WelcomeVerificationFragment.this.contentContainer.getHeight();
            WelcomeVerificationFragment.this.backgroundImageView.getLayoutParams().height = contentHeight;
            WelcomeVerificationFragment.this.backgroundImageView.setScaleType(ScaleType.CENTER_CROP);
            WelcomeVerificationFragment.this.backgroundImageView.setImageResource(C0880R.C0881drawable.host_greeting);
            WelcomeVerificationFragment.this.backgroundImageView.requestLayout();
            WelcomeVerificationFragment.this.gradientImage.getLayoutParams().height = contentHeight;
            WelcomeVerificationFragment.this.gradientImage.setScaleType(ScaleType.CENTER);
            WelcomeVerificationFragment.this.gradientImage.setImageResource(C0880R.C0881drawable.full_background_gradient);
            WelcomeVerificationFragment.this.gradientImage.requestLayout();
        }
    };
    @State
    Listing listing;
    @BindView
    TextView stepsRemainingTextView;
    @BindView
    TextView userWelcomeHeader;
    @State
    VerificationFlow verificationFlow;

    public static WelcomeVerificationFragment newInstance() {
        WelcomeVerificationFragment fragment = new WelcomeVerificationFragment();
        Bundle args = new Bundle();
        args.putInt("arg_verification_flow", VerificationFlow.Onboarding.ordinal());
        fragment.setArguments(args);
        return fragment;
    }

    public static WelcomeVerificationFragment newInstanceForBooking(Listing listing2) {
        WelcomeVerificationFragment fragment = new WelcomeVerificationFragment();
        Bundle args = new Bundle();
        args.putInt("arg_verification_flow", VerificationFlow.Booking.ordinal());
        args.putParcelable(ARG_LISTING, listing2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.verificationFlow = VerificationFlow.values()[getArguments().getInt("arg_verification_flow")];
            this.listing = (Listing) getArguments().getParcelable(ARG_LISTING);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        bindViews(view);
        if (this.verificationFlow == VerificationFlow.Onboarding) {
            setUpOnboardingWelcome();
        } else {
            setUpBookingWelcome();
        }
        return view;
    }

    public void onStart() {
        super.onStart();
        if (this.verificationFlow == VerificationFlow.Onboarding) {
            track("activate_account_intro", "intro_signup", "impression");
        } else {
            track("activate_account_intro", "intro_booking", "impression");
        }
    }

    public void onDestroyView() {
        if (this.contentContainer != null) {
            this.contentContainer.removeOnLayoutChangeListener(this.listener);
        }
        super.onDestroyView();
    }

    @OnClick
    public void doneWithWelcome() {
        if (this.verificationFlow == VerificationFlow.Onboarding) {
            track("activate_account_intro", "intro_signup", "click", RegistrationAnalytics.NEXT_BUTTON);
        } else {
            track("activate_account_intro", "intro_booking", "click", RegistrationAnalytics.NEXT_BUTTON);
        }
        ((VerificationsActivity) getActivity()).doneWithWelcome();
    }

    private int getLayoutId() {
        return VerificationFlowToLayoutId.find(this.verificationFlow).getWelcomeLayoutId();
    }

    private void setUpOnboardingWelcome() {
        String name = this.mAccountManager.getCurrentUser().getFirstName();
        this.userWelcomeHeader.setText(getString(C0880R.string.verifications_welcome_welcome_user, name));
        setUpBackgroundImage();
    }

    private void setUpBookingWelcome() {
        ImageUtils.setImageUrlForUser(this.hostHaloImageView, this.listing.getHost());
        ImageUtils.setImageUrlForUser(this.guestHaloImageView, this.mAccountManager.getCurrentUser());
        String hostFirstName = this.listing.getHost().getFirstName();
        this.bookingHeaderTextView.setText(getString(C0880R.string.verifications_welcome_booking_get_ready, hostFirstName));
        VerificationsActivity activity = (VerificationsActivity) getActivity();
        int numStepsCompleted = activity.getNumCompletedVerifications();
        int totalSteps = activity.getNumVerifications();
        this.stepsRemainingTextView.setText(getString(C0880R.string.verifications_welcome_booking_steps_remaining, Integer.valueOf(numStepsCompleted), Integer.valueOf(totalSteps)));
    }

    private void setUpBackgroundImage() {
        this.contentContainer.addOnLayoutChangeListener(this.listener);
    }
}
