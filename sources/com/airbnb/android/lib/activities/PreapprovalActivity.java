package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.PreapproveInquiryFragment;
import com.airbnb.android.lib.fragments.SouthKoreanCancellationPolicyFragment;
import com.airbnb.android.lib.fragments.SouthKoreanCancellationPolicyFragment.Listener;
import icepick.State;

public class PreapprovalActivity extends AirActivity implements Listener {
    private static final String ARG_PROVIDER = "trip_provider";
    private static final String SK_CANCELLATION_TAG = SouthKoreanCancellationPolicyFragment.class.getSimpleName();
    @State
    TripInformationProvider provider;

    public static Intent intentForProvider(Context context, TripInformationProvider provider2) {
        return new Intent(context, PreapprovalActivity.class).putExtra("trip_provider", (Parcelable) Check.notNull(provider2));
    }

    public void onAcceptCancellationAgreement() {
        showPreapprovalFragment(true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            this.provider = (TripInformationProvider) getIntent().getParcelableExtra("trip_provider");
            if (this.provider.isKoreanStrictBooking()) {
                showFragment(SouthKoreanCancellationPolicyFragment.newInstance(C0880R.string.preapprove_reservation_south_korean_cancellation_policy, C0880R.string.preapprove_south_korean_cancellation_policy_host_agreement, this.provider), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, false, SK_CANCELLATION_TAG);
                return;
            }
            showPreapprovalFragment(false);
        }
    }

    private void showPreapprovalFragment(boolean hostAgreedSouthKoreanPreapproval) {
        showFragment(PreapproveInquiryFragment.newInstanceForProvider(this.provider, hostAgreedSouthKoreanPreapproval), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }
}
