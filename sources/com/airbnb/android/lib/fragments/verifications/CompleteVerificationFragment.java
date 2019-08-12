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
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.VerificationsActivity;
import com.airbnb.android.lib.enums.VerificationFlowToLayoutId;
import icepick.State;

public class CompleteVerificationFragment extends VerificationsFragment {
    private static final String ARG_VERIFICATION_FLOW = "arg_verification_flow";
    @BindView
    ImageView backgroundImageView;
    @BindView
    TextView bookingSubHeader;
    @BindView
    View contentContainer;
    @BindView
    ImageView gradientImage;
    private final OnLayoutChangeListener listener = new OnLayoutChangeListener() {
        public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            int contentHeight = CompleteVerificationFragment.this.contentContainer.getHeight();
            CompleteVerificationFragment.this.backgroundImageView.getLayoutParams().height = contentHeight;
            CompleteVerificationFragment.this.backgroundImageView.setScaleType(ScaleType.CENTER_CROP);
            CompleteVerificationFragment.this.backgroundImageView.setImageResource(C0880R.C0881drawable.guests_bike);
            CompleteVerificationFragment.this.backgroundImageView.requestLayout();
            CompleteVerificationFragment.this.gradientImage.getLayoutParams().height = contentHeight;
            CompleteVerificationFragment.this.gradientImage.setScaleType(ScaleType.CENTER);
            CompleteVerificationFragment.this.gradientImage.setImageResource(C0880R.C0881drawable.full_background_gradient);
            CompleteVerificationFragment.this.gradientImage.requestLayout();
        }
    };
    @State
    VerificationFlow verificationFlow;

    public static CompleteVerificationFragment newInstance() {
        Bundle args = new Bundle();
        args.putInt("arg_verification_flow", VerificationFlow.Onboarding.ordinal());
        CompleteVerificationFragment fragment = new CompleteVerificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static CompleteVerificationFragment newInstanceForBooking() {
        Bundle args = new Bundle();
        args.putInt("arg_verification_flow", VerificationFlow.Booking.ordinal());
        CompleteVerificationFragment fragment = new CompleteVerificationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.verificationFlow = VerificationFlow.values()[getArguments().getInt("arg_verification_flow")];
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        bindViews(view);
        if (this.bookingSubHeader != null) {
            String userName = this.mAccountManager.getCurrentUser().getFirstName();
            this.bookingSubHeader.setText(getString(C0880R.string.verifications_complete_thanks_user, userName));
        }
        setUpBackgroundImage();
        return view;
    }

    public void onStart() {
        super.onStart();
        if (this.verificationFlow == VerificationFlow.Onboarding) {
            track("activate_account_complete", "complete_signup", "impression");
        } else {
            track("activate_account_complete", "complete_booking", "impression");
        }
    }

    public void onDestroyView() {
        this.contentContainer.removeOnLayoutChangeListener(this.listener);
        super.onDestroyView();
    }

    private int getLayoutId() {
        return VerificationFlowToLayoutId.find(this.verificationFlow).getCompleteLayoutId();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickCompleteButton() {
        if (this.verificationFlow == VerificationFlow.Onboarding) {
            track("activate_account_complete", "complete_signup", "click", RegistrationAnalytics.NEXT_BUTTON);
        } else {
            track("activate_account_complete", "complete_booking", "click", RegistrationAnalytics.NEXT_BUTTON);
        }
        ((VerificationsActivity) getActivity()).doneWithComplete();
    }

    private void setUpBackgroundImage() {
        this.contentContainer.addOnLayoutChangeListener(this.listener);
    }
}
