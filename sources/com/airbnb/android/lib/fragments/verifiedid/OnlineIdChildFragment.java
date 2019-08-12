package com.airbnb.android.lib.fragments.verifiedid;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.enums.AuthorizeService;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.VerifiedIdActivity;
import com.airbnb.android.utils.Strap;

public class OnlineIdChildFragment extends Fragment implements VerifiedIdStrapper {
    @BindView
    Button mFacebookButton;
    @BindView
    Button mGoogleButton;
    @BindView
    Button mLinkedInButton;
    SharedPrefsHelper mPrefsHelper;
    @BindView
    Button mWeiboButton;

    public static OnlineIdChildFragment newInstance() {
        return new OnlineIdChildFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_child_verified_id_online, container, false);
        ButterKnife.bind(this, view);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        ((TextView) view.findViewById(C0880R.C0882id.online_privacy_link)).setOnClickListener(OnlineIdChildFragment$$Lambda$1.lambdaFactory$(this));
        this.mFacebookButton.setOnClickListener(OnlineIdChildFragment$$Lambda$2.lambdaFactory$(this));
        this.mLinkedInButton.setOnClickListener(OnlineIdChildFragment$$Lambda$3.lambdaFactory$(this));
        if (!MiscUtils.hasGooglePlayServices(getActivity())) {
            this.mGoogleButton.setVisibility(8);
        } else {
            this.mGoogleButton.setOnClickListener(OnlineIdChildFragment$$Lambda$4.lambdaFactory$(this));
        }
        this.mWeiboButton.setOnClickListener(OnlineIdChildFragment$$Lambda$5.lambdaFactory$(this));
        if (this.mPrefsHelper.getLoginServiceType() == AuthorizeService.GOOGLE.ordinal()) {
            this.mGoogleButton.setVisibility(8);
        } else if (this.mPrefsHelper.getLoginServiceType() == AuthorizeService.FACEBOOK.ordinal()) {
            this.mFacebookButton.setVisibility(8);
        }
        if (AppLaunchUtils.isUserInChina()) {
            prioritizeWeibo();
            this.mFacebookButton.setVisibility(8);
        }
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(OnlineIdChildFragment onlineIdChildFragment, View v) {
        VerifiedIdAnalytics.trackOnlineStartLearnMore(onlineIdChildFragment.getVerifiedIdAnalyticsStrap());
        onlineIdChildFragment.startActivity(HelpCenterIntents.intentForHelpCenterArticle(onlineIdChildFragment.getContext(), 450).title(C0880R.string.verified_id_learn_more).toIntent());
    }

    public void enableButtons(boolean enable) {
        this.mFacebookButton.setEnabled(enable);
        this.mLinkedInButton.setEnabled(enable);
        this.mGoogleButton.setEnabled(enable);
        this.mWeiboButton.setEnabled(enable);
    }

    private void prioritizeWeibo() {
        ViewGroup buttonParent = (ViewGroup) this.mWeiboButton.getParent();
        buttonParent.removeView(this.mWeiboButton);
        buttonParent.addView(this.mWeiboButton, buttonParent.indexOfChild(this.mFacebookButton));
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((VerifiedIdActivity) getActivity()).getReservationIdStringForAnalytics());
    }
}
