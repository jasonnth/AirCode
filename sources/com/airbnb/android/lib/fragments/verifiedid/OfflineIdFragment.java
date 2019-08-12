package com.airbnb.android.lib.fragments.verifiedid;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.android.core.views.LoaderFrame.LoaderFrameDisplay;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.OfficialIdActivity;
import com.airbnb.android.lib.activities.VerifiedIdActivity;
import com.airbnb.android.lib.views.CircleBadgeView;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;

public class OfflineIdFragment extends BaseVerifiedIdFragment {
    private static final String ARG_SHOW_SESAME_VERIFICATION = "arg_show_sesame_verification";
    static final int RC_CONNECT_SESAME = 39002;
    static final int SCAN_ID_REQUEST = 39001;
    private CircleBadgeView mCircleBadgeView;
    private String mVerificationSuccessDescription;
    private boolean showSesameVerification;

    enum VerificationMethod {
        GovernmentId,
        Sesame
    }

    public static OfflineIdFragment newInstance(boolean showSesameVerification2) {
        return (OfflineIdFragment) ((FragmentBundleBuilder) FragmentBundler.make(new OfflineIdFragment()).putBoolean(ARG_SHOW_SESAME_VERIFICATION, showSesameVerification2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_verified_id_offline, container, false);
        this.mCircleBadgeView = (CircleBadgeView) view.findViewById(C0880R.C0882id.circle_badge_view_offline);
        this.mCircleBadgeView.initializeAsInactiveBadge();
        this.showSesameVerification = getArguments().getBoolean(ARG_SHOW_SESAME_VERIFICATION, false);
        showFragment(this.showSesameVerification ? SesameVerificationChildFragment.newInstance() : OfflineIdChildFragment.newInstance(this.showSesameVerification));
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case SCAN_ID_REQUEST /*39001*/:
                    this.mVerificationSuccessDescription = data.getStringExtra(OfficialIdActivity.ID_TYPE_EXTRA);
                    return;
                case RC_CONNECT_SESAME /*39002*/:
                    this.mVerificationSuccessDescription = getString(C0880R.string.verified_id_connect_sesame_success_description);
                    return;
                default:
                    super.onActivityResult(requestCode, resultCode, data);
                    return;
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public CircleBadgeView getCircleBadgeView() {
        return this.mCircleBadgeView;
    }

    public int getContentFragmentId() {
        return C0880R.C0882id.verified_id_offline_fragment;
    }

    public String getVerificationSuccessDescription() {
        return this.mVerificationSuccessDescription;
    }

    public void setPendingState() {
        ((LoaderFrameDisplay) getActivity()).displayLoaderFrame(true);
        ((OfflineIdChildFragment) getChildFragmentManager().findFragmentById(C0880R.C0882id.verified_id_online_fragment)).disableButtons();
    }

    public void setActiveState() {
        ((LoaderFrameDisplay) getActivity()).displayLoaderFrame(false);
        ((OfflineIdChildFragment) getChildFragmentManager().findFragmentById(C0880R.C0882id.verified_id_online_fragment)).enableButtons();
    }

    /* access modifiers changed from: 0000 */
    public void switchToVerificationMethod(VerificationMethod method) {
        Fragment contentFrament;
        int titleRes;
        switch (method) {
            case Sesame:
                contentFrament = SesameVerificationChildFragment.newInstance();
                titleRes = C0880R.string.verified_id_connect_sesame_credit_title;
                break;
            case GovernmentId:
                contentFrament = OfflineIdChildFragment.newInstance(this.showSesameVerification);
                titleRes = C0880R.string.verified_id_spb_scan_official_id;
                break;
            default:
                throw new IllegalArgumentException("Unexpected verification method: " + method.name());
        }
        showFragment(contentFrament);
        ((VerifiedIdActivity) getActivity()).updateProgressBarTitle(titleRes);
    }

    private void showFragment(Fragment contentFragment) {
        getChildFragmentManager().beginTransaction().replace(C0880R.C0882id.verified_id_offline_fragment, contentFragment).commitAllowingStateLoss();
    }
}
