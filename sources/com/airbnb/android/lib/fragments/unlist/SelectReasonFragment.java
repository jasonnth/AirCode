package com.airbnb.android.lib.fragments.unlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.OnClick;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.UnlistActivity;
import com.airbnb.android.lib.analytics.LegacyUnlistAnalytics;
import com.airbnb.android.lib.enums.LegacyUnlistReason;

public class SelectReasonFragment extends BaseSnoozeListingFragment {
    public static SelectReasonFragment newInstance() {
        return new SelectReasonFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_select_reason, container, false);
        bindViews(view);
        return view;
    }

    /* access modifiers changed from: protected */
    public int getTitle() {
        return C0880R.string.unlist;
    }

    @OnClick
    public void onClickUnlist(View view) {
        LegacyUnlistReason unlistReason = LegacyUnlistReason.getUnlistStateForResourceId(view.getId());
        LegacyUnlistAnalytics.trackSelectUnlistReason(this.listing, unlistReason.getReasonEvent());
        ((UnlistActivity) getActivity()).doneWithSelectReason(unlistReason.getReason());
    }

    @OnClick
    public void onClickSnoozeMode() {
        LegacyUnlistAnalytics.trackSelectUnlistReason(this.listing, "unlist_temporarily");
        ((UnlistActivity) getActivity()).doneWithSelectReason(3);
    }

    @OnClick
    public void onClickUnlistFeedback() {
        LegacyUnlistAnalytics.trackSelectUnlistReason(this.listing, "other_reasons");
        ((UnlistActivity) getActivity()).doneWithSelectReason(4);
    }
}
