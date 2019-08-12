package com.airbnb.android.lib.fragments.unlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.enums.LegacyUnlistReason;
import com.airbnb.android.lib.views.GroupedCounter;

public class UnlistTooMuchWorkFragment extends BaseSnoozeListingFragment {
    @BindView
    GroupedCounter snoozeEndDateCounter;

    public static UnlistTooMuchWorkFragment newInstance() {
        return new UnlistTooMuchWorkFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_unlist_too_much_work, container, false);
        bindViews(view);
        return view;
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void snoozeListingFromToday() {
        snoozeListing(AirDate.today(), AirDate.today().plusMonths(this.snoozeEndDateCounter.getSelectedValue()));
    }

    /* access modifiers changed from: protected */
    public LegacyUnlistReason getUnlistReason() {
        return LegacyUnlistReason.TooMuchWork;
    }
}
