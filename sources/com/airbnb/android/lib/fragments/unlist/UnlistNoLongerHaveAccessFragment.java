package com.airbnb.android.lib.fragments.unlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.android.core.intents.ListYourSpaceIntents;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.LegacyUnlistAnalytics;
import com.airbnb.android.lib.enums.LegacyUnlistReason;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;
import com.airbnb.p027n2.utils.ClickableLinkUtils;

public class UnlistNoLongerHaveAccessFragment extends BaseSnoozeListingFragment implements LinkOnClickListener {
    @BindView
    TextView textView;

    public static UnlistNoLongerHaveAccessFragment newInstance() {
        return new UnlistNoLongerHaveAccessFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_unlist, container, false);
        bindViews(view);
        ClickableLinkUtils.setupClickableTextView(this.textView, getString(C0880R.string.ml_unlisting_reason_unlist_subtitle), getString(C0880R.string.ml_unlisting_reason_unlist_subtitle_link_text), C0880R.color.canonical_press_darken, (LinkOnClickListener) this);
        return view;
    }

    public void onClickLink(int linkIndex) {
        LegacyUnlistAnalytics.trackClickLinkInUnlistDialog(this.listing, "list_your_space");
        startActivity(ListYourSpaceIntents.intentForNewListing(getContext(), "unlist", null));
    }

    /* access modifiers changed from: protected */
    public LegacyUnlistReason getUnlistReason() {
        return LegacyUnlistReason.NoLongerHaveAccess;
    }
}
