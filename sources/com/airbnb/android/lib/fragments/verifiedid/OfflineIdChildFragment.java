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
import butterknife.OnClick;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.OfficialIdActivity;
import com.airbnb.android.lib.activities.VerifiedIdActivity;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import icepick.State;

public class OfflineIdChildFragment extends Fragment implements VerifiedIdStrapper {
    private static final String ARG_SHOW_SESAME_SWITCHER = "arg_show_sesame_switcher";
    @BindView
    Button beginButton;
    @BindView
    TextView privacyText;
    @BindView
    View sesameCreditSwitcherContainer;
    @State
    boolean showSesameCreditSwitcher;
    VerifiedIdAnalytics verifiedIdAnalytics;

    public static OfflineIdChildFragment newInstance(boolean showSesameCreditSwitcher2) {
        return (OfflineIdChildFragment) ((FragmentBundleBuilder) FragmentBundler.make(new OfflineIdChildFragment()).putBoolean(ARG_SHOW_SESAME_SWITCHER, showSesameCreditSwitcher2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        View view = inflater.inflate(C0880R.layout.fragment_child_verified_id_offline, container, false);
        ButterKnife.bind(this, view);
        String learnMoreText = getString(C0880R.string.verified_id_learn_more);
        ClickableLinkUtils.setupClickableTextView(this.privacyText, String.format("%s %s", new Object[]{getString(C0880R.string.verified_id_offline_privacy), learnMoreText}), learnMoreText, C0880R.color.canonical_press_darken, OfflineIdChildFragment$$Lambda$1.lambdaFactory$(this));
        this.beginButton.setOnClickListener(OfflineIdChildFragment$$Lambda$2.lambdaFactory$(this));
        if (savedInstanceState == null) {
            VerifiedIdAnalytics.trackOfflineStartView(getVerifiedIdAnalyticsStrap());
            VerifiedIdAnalytics.trackHealth("offline", "start");
            this.showSesameCreditSwitcher = getArguments().getBoolean(ARG_SHOW_SESAME_SWITCHER, false);
        }
        ViewUtils.setVisibleIf(this.sesameCreditSwitcherContainer, this.showSesameCreditSwitcher);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$1(OfflineIdChildFragment offlineIdChildFragment, View v) {
        VerifiedIdActivity activity = (VerifiedIdActivity) offlineIdChildFragment.getActivity();
        VerifiedIdAnalytics.trackOfflineStartScanId(offlineIdChildFragment.getVerifiedIdAnalyticsStrap());
        offlineIdChildFragment.getParentFragment().startActivityForResult(OfficialIdActivity.intentForVerifiedId(activity, activity.getNumberCompletedStep(), activity.getReservationId(), activity.getCheckpointVerifications()), 39001);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void switchToSesameCredit() {
        VerifiedIdAnalytics.trackOfflineVerificationMethodSwitch(getVerifiedIdAnalyticsStrap(), "SWITCH_TO_SESAME");
        ((OfflineIdFragment) getParentFragment()).switchToVerificationMethod(VerificationMethod.Sesame);
    }

    public void disableButtons() {
        this.beginButton.setEnabled(false);
    }

    public void enableButtons() {
        this.beginButton.setEnabled(true);
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", ((VerifiedIdActivity) getActivity()).getReservationIdStringForAnalytics());
    }
}
