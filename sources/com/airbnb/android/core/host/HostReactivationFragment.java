package com.airbnb.android.core.host;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.activities.ModalActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.host.stats.HostReactivationAnalytics;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.HostStandard;
import com.airbnb.android.core.requests.HostReactivationCopyRequest;
import com.airbnb.android.core.requests.UpdateUserRequest;
import com.airbnb.android.core.responses.HostReactivationCopyResponse;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import icepick.State;

public class HostReactivationFragment extends AirFragment {
    @BindView
    FixedActionFooter buttonFooter;
    final RequestListener<HostReactivationCopyResponse> copyRequestListener = new C0699RL().onResponse(HostReactivationFragment$$Lambda$1.lambdaFactory$(this)).onError(HostReactivationFragment$$Lambda$2.lambdaFactory$(this)).onComplete(HostReactivationFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    LinkActionRow helpLinkText;
    /* access modifiers changed from: 0000 */
    @State
    public HostStandard hostStandard;
    @BindView
    AirTextView messageText;
    final RequestListener<UserResponse> reactivateHostListener = new C0699RL().onResponse(HostReactivationFragment$$Lambda$4.lambdaFactory$(this)).onError(HostReactivationFragment$$Lambda$5.lambdaFactory$(this)).build();
    @BindView
    RefreshLoader refreshLoader;
    @BindView
    AirToolbar toolbar;

    public static Intent createIntent(Context context) {
        return ModalActivity.intentForFragment(context, new HostReactivationFragment());
    }

    static /* synthetic */ void lambda$new$4(HostReactivationFragment hostReactivationFragment, UserResponse ignored) {
        hostReactivationFragment.buttonFooter.setButtonText(C0716R.string.host_reactivation_success);
        hostReactivationFragment.getActivity().finish();
    }

    static /* synthetic */ void lambda$new$5(HostReactivationFragment hostReactivationFragment, AirRequestNetworkException e) {
        hostReactivationFragment.buttonFooter.setButtonLoading(false);
        NetworkUtil.tryShowErrorWithSnackbar(hostReactivationFragment.getView(), e);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!this.mAccountManager.getCurrentUser().isSuspended()) {
            Toast.makeText(getActivity(), C0716R.string.host_reactivation_already_reactivated, 0).show();
            getActivity().finish();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0716R.layout.fragment_host_reactivation, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (this.hostStandard != null) {
            updateUICopy();
        } else {
            HostReactivationAnalytics.trackImpression();
            makeHostReactivationCopyRequest();
        }
        return view;
    }

    @OnClick
    public void onHelpLinkClick() {
        startActivity(WebViewIntentBuilder.newBuilder(getContext(), getString(C0716R.string.airbnb_base_url) + this.hostStandard.getReactivationHelpLink()).toIntent());
    }

    @OnClick
    public void onReactivateButtonClick() {
        HostReactivationAnalytics.trackReactivateClick();
        this.buttonFooter.setButtonLoading(true);
        UpdateUserRequest.forReactivate(this.reactivateHostListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void updateUICopy() {
        boolean hasHelpText = true;
        this.refreshLoader.setVisibility(8);
        if (this.hostStandard != null) {
            if (!TextUtils.isEmpty(this.hostStandard.getReactivationBodyText())) {
                this.messageText.setText(this.hostStandard.getReactivationBodyText());
            }
            if (!(!TextUtils.isEmpty(this.hostStandard.getReactivationHelpLink())) || TextUtils.isEmpty(this.hostStandard.getReactivationHelpText())) {
                hasHelpText = false;
            }
            ViewLibUtils.setVisibleIf(this.helpLinkText, hasHelpText);
            this.helpLinkText.setText((CharSequence) this.hostStandard.getReactivationHelpText());
            ViewLibUtils.setVisibleIf(this.buttonFooter, this.hostStandard.isCanReactivate());
        }
        this.messageText.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void makeHostReactivationCopyRequest() {
        this.refreshLoader.setVisibility(0);
        this.messageText.setVisibility(8);
        this.helpLinkText.setVisibility(8);
        this.buttonFooter.setVisibility(8);
        new HostReactivationCopyRequest(this.copyRequestListener).execute(this.requestManager);
    }
}
