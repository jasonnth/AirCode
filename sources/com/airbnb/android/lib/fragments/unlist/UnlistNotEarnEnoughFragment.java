package com.airbnb.android.lib.fragments.unlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.LegacyUnlistAnalytics;
import com.airbnb.android.lib.enums.LegacyUnlistReason;
import com.airbnb.android.lib.views.GroupedCheck;

public class UnlistNotEarnEnoughFragment extends BaseSnoozeListingFragment {
    @BindView
    GroupedCheck sendAlertsCheckbox;

    public static UnlistNotEarnEnoughFragment newInstance() {
        return new UnlistNotEarnEnoughFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_unlist_not_earn_enough, container, false);
        bindViews(view);
        return view;
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void keepListingListed() {
        if (this.sendAlertsCheckbox.isSelected()) {
            LegacyUnlistAnalytics.trackSendAlertsForPopularTravelDates(this.listing);
        }
        getActivity().finish();
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void unlistListing() {
        LegacyUnlistAnalytics.trackSubmitUnlistWithSubscriptionToAlert(this.listing, this.sendAlertsCheckbox.isChecked());
        super.processUnlistListing();
    }

    /* access modifiers changed from: protected */
    public LegacyUnlistReason getUnlistReason() {
        return LegacyUnlistReason.NotEarnEnough;
    }
}
