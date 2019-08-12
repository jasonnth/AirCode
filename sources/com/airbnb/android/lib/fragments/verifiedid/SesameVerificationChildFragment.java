package com.airbnb.android.lib.fragments.verifiedid;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.VerifiedIdActivity;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.utils.ClickableLinkUtils;

public class SesameVerificationChildFragment extends Fragment implements VerifiedIdStrapper {
    @BindView
    TextView privacyText;
    VerifiedIdAnalytics verifiedIdAnalytics;

    public static SesameVerificationChildFragment newInstance() {
        return new SesameVerificationChildFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        View view = inflater.inflate(C0880R.layout.fragment_sesame_verification, container, false);
        ButterKnife.bind(this, view);
        String learnMoreText = getString(C0880R.string.verified_id_learn_more);
        ClickableLinkUtils.setupClickableTextView(this.privacyText, String.format("%s %s", new Object[]{getString(C0880R.string.verified_id_connect_sesame_privacy_policy), learnMoreText}), learnMoreText, C0880R.color.canonical_press_darken, SesameVerificationChildFragment$$Lambda$1.lambdaFactory$(this));
        if (savedInstanceState == null) {
            VerifiedIdAnalytics.trackSesameStartView(getVerifiedIdAnalyticsStrap());
        }
        return view;
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((VerifiedIdActivity) getActivity()).getReservationIdStringForAnalytics());
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void connectSesame() {
        VerifiedIdAnalytics.trackSesameStartEnterInfo(getVerifiedIdAnalyticsStrap());
        getParentFragment().startActivityForResult(AutoAirActivity.intentForFragment(getContext(), SesameVerificationConnectFragment.class, null, C0880R.string.verified_id_connect_sesame_enter_info), 39002);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void switchToVerifyGovID() {
        VerifiedIdAnalytics.trackOfflineVerificationMethodSwitch(getVerifiedIdAnalyticsStrap(), "SWITCH_TO_OFFLINE");
        ((OfflineIdFragment) getParentFragment()).switchToVerificationMethod(VerificationMethod.GovernmentId);
    }
}
