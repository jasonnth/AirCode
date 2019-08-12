package com.airbnb.android.lib.fragments.unlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.OnClick;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.enums.LegacyUnlistReason;

public class UnlistNegativeExperienceFragment extends BaseSnoozeListingFragment {
    public static UnlistNegativeExperienceFragment newInstance() {
        return new UnlistNegativeExperienceFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_unlist_negative_experience, container, false);
        bindViews(view);
        return view;
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void unlistAndContactUs() {
        unlistListing();
        WebViewIntentBuilder.startAuthenticatedMobileWebActivity(getActivity(), getString(C0880R.string.contact_cx_url));
    }

    /* access modifiers changed from: protected */
    public LegacyUnlistReason getUnlistReason() {
        return LegacyUnlistReason.NegativeExperience;
    }
}
