package com.airbnb.android.lib.fragments.unlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.LegacyUnlistAnalytics;

public class UnlistOtherReasonsFragment extends BaseSnoozeListingFragment {
    @BindView
    EditText unlistReasonTextView;

    public static UnlistOtherReasonsFragment newInstance() {
        return new UnlistOtherReasonsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_unlist_other_reasons, container, false);
        bindViews(view);
        return view;
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void unlistListing() {
        LegacyUnlistAnalytics.trackSubmitUnlistWithOtherReason(this.listing, this.unlistReasonTextView.getText().toString());
        super.processUnlistListing();
    }
}
