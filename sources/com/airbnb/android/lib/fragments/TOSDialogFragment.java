package com.airbnb.android.lib.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.FragmentLauncher;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager.TargetUserType;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.requests.AcceptTermsOfServiceRequest;
import com.airbnb.android.core.views.AirWebView;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import icepick.State;
import java.util.Locale;

public class TOSDialogFragment extends AirDialogFragment implements FragmentLauncher {
    private static final String ARG_IS_COMMUNITY_COMMITMENT_NEEDED = "is_community_commitment_needed";
    private static final String ARG_USER_TYPE = "user_type";
    private static final String URL_PARAMS = ("?hide_nav=true&locale=" + Locale.getDefault().getLanguage());
    @BindView
    AirWebView mAirWebView;
    AirbnbApi mAirbnbApi;
    @BindView
    CheckBox mCheckBox;
    @State
    boolean responded;

    public static TOSDialogFragment newInstance(boolean isCommunityCommitmentRequired, TargetUserType userType) {
        return (TOSDialogFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new TOSDialogFragment()).putSerializable(ARG_USER_TYPE, userType)).putBoolean(ARG_IS_COMMUNITY_COMMITMENT_NEEDED, isCommunityCommitmentRequired)).build();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        setStyle(1, C0880R.C0885style.Theme_Airbnb_DialogNoTitle);
        View view = LayoutInflater.from(getContext()).inflate(C0880R.layout.dialog_tos, null);
        ButterKnife.bind(this, view);
        this.mCheckBox.setChecked(false);
        this.mCheckBox.setOnCheckedChangeListener(TOSDialogFragment$$Lambda$1.lambdaFactory$(this));
        this.mAirWebView.loadUrl(getString(C0880R.string.tos_url_terms) + URL_PARAMS);
        AlertDialog dialog = new Builder(getActivity()).setView(view).setPositiveButton(C0880R.string.tos_agree, TOSDialogFragment$$Lambda$2.lambdaFactory$(this)).setNegativeButton(C0880R.string.tos_disagree, TOSDialogFragment$$Lambda$3.lambdaFactory$(this)).setOnKeyListener(TOSDialogFragment$$Lambda$4.lambdaFactory$(this)).create();
        setCancelable(false);
        return dialog;
    }

    static /* synthetic */ boolean lambda$onCreateDialog$3(TOSDialogFragment tOSDialogFragment, DialogInterface dialog1, int keyCode, KeyEvent event) {
        if (keyCode != 4 || !tOSDialogFragment.mAirWebView.canGoBack()) {
            return false;
        }
        tOSDialogFragment.mAirWebView.goBack();
        return true;
    }

    public void onResume() {
        super.onResume();
        updatedButtons();
    }

    public void onDestroyView() {
        this.mAirWebView.onDestroy();
        super.onDestroyView();
    }

    /* access modifiers changed from: private */
    public void updatedButtons() {
        boolean z;
        boolean z2 = true;
        AlertDialog dialog = (AlertDialog) getDialog();
        Button accept = dialog.getButton(-1);
        if (this.responded || !this.mCheckBox.isChecked()) {
            z = false;
        } else {
            z = true;
        }
        accept.setEnabled(z);
        Button decline = dialog.getButton(-2);
        if (this.responded) {
            z2 = false;
        }
        decline.setEnabled(z2);
    }

    /* access modifiers changed from: private */
    public void accept() {
        if (!this.responded) {
            this.responded = true;
            new AcceptTermsOfServiceRequest().execute(this.requestManager);
            CommunityCommitmentManager.launchCommunityCommitmentIfNeeded(getArguments().getBoolean(ARG_IS_COMMUNITY_COMMITMENT_NEEDED), (TargetUserType) getArguments().getSerializable(ARG_USER_TYPE), getActivity());
        }
    }

    /* access modifiers changed from: private */
    public void decline() {
        if (!this.responded) {
            this.responded = true;
            this.mAirbnbApi.logout();
            if (getActivity() != null) {
                WebViewDialogFragment.newInstance(C0880R.string.app_name, getString(C0880R.string.tos_url_terms_disagree) + URL_PARAMS, false, 0, null).show(getActivity().getSupportFragmentManager(), (String) null);
            }
        }
    }

    public Bundle getDummyArguments() {
        return new Bundle();
    }
}
