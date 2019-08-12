package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.host.stats.HostReactivationAnalytics;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.requests.HostReactivationCopyRequest;
import com.airbnb.android.core.requests.UpdateUserRequest;
import com.airbnb.android.core.responses.HostReactivationCopyResponse;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.AutoAirModalLargeActivity;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.ViewLibUtils;
import icepick.State;

public class LegacyHostReactivationFragment extends AirFragment {
    @State
    String bodyText;
    @State
    boolean canReactivate;
    @BindView
    TextView educationTextView;
    @State
    String helpLinkTitle;
    @State
    String helpLinkUrl;
    @BindView
    TextView helpcenterLinkTextView;
    @BindView
    AirButton reactivateButton;

    public static Intent createIntent(Context context) {
        return AutoAirModalLargeActivity.intentForFragment(context, LegacyHostReactivationFragment.class, (Bundle) null, C0880R.string.host_reactivation_reactivate_title);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_host_reactivation_legacy, container, false);
        bindViews(view);
        setLoaderFrameBackground(C0880R.color.c_gray_6);
        if (this.bodyText != null) {
            updateUiFields();
        } else {
            HostReactivationAnalytics.trackImpression();
            loadReactivationInfoFromNetwork();
        }
        return view;
    }

    /* access modifiers changed from: private */
    public void updateUiFields() {
        if (!TextUtils.isEmpty(this.bodyText)) {
            this.educationTextView.setText(this.bodyText);
        }
        if (!TextUtils.isEmpty(this.helpLinkTitle)) {
            this.helpcenterLinkTextView.setText(this.helpLinkTitle);
        }
        ViewLibUtils.setVisibleIf(this.reactivateButton, this.canReactivate);
        ViewUtils.setGoneIf(this.helpcenterLinkTextView, TextUtils.isEmpty(this.helpLinkTitle) || TextUtils.isEmpty(this.helpLinkUrl));
    }

    private void loadReactivationInfoFromNetwork() {
        showLoader(true);
        new HostReactivationCopyRequest(new NonResubscribableRequestListener<HostReactivationCopyResponse>() {
            public void onResponse(HostReactivationCopyResponse response) {
                if (!response.hostStandard.isSuspended()) {
                    Toast.makeText(LegacyHostReactivationFragment.this.getActivity(), C0880R.string.host_reactivation_already_reactivated, 0).show();
                    LegacyHostReactivationFragment.this.getActivity().finish();
                    return;
                }
                LegacyHostReactivationFragment.this.bodyText = LegacyHostReactivationFragment.this.updateStringSafe(LegacyHostReactivationFragment.this.bodyText, response.hostStandard.getReactivationBodyText());
                LegacyHostReactivationFragment.this.helpLinkTitle = LegacyHostReactivationFragment.this.updateStringSafe(LegacyHostReactivationFragment.this.helpLinkTitle, response.hostStandard.getReactivationHelpText());
                LegacyHostReactivationFragment.this.helpLinkUrl = LegacyHostReactivationFragment.this.updateStringSafe(LegacyHostReactivationFragment.this.helpLinkUrl, response.hostStandard.getReactivationHelpLink());
                LegacyHostReactivationFragment.this.canReactivate = response.hostStandard.isCanReactivate();
                LegacyHostReactivationFragment.this.updateUiFields();
                LegacyHostReactivationFragment.this.showLoader(false);
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                LegacyHostReactivationFragment.this.showLoader(false);
            }
        }).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public String updateStringSafe(String oldString, String newString) {
        return !TextUtils.isEmpty(newString) ? newString : oldString;
    }

    @OnClick
    public void onReactivationInfoLinkClick() {
        if (this.helpLinkUrl.startsWith("/help/article")) {
            this.helpLinkUrl = getString(C0880R.string.airbnb_base_url) + this.helpLinkUrl;
        }
        startActivity(WebViewIntentBuilder.newBuilder(getActivity(), this.helpLinkUrl).toIntent());
    }

    @OnClick
    public void onReactivateButtonClick(final View view) {
        view.setEnabled(false);
        HostReactivationAnalytics.trackReactivateClick();
        UpdateUserRequest.forReactivate(new NonResubscribableRequestListener<UserResponse>() {
            public void onResponse(UserResponse data) {
                Toast.makeText(LegacyHostReactivationFragment.this.getActivity(), C0880R.string.listings_have_been_reactivated, 0).show();
                LegacyHostReactivationFragment.this.getActivity().finish();
            }

            public void onErrorResponse(AirRequestNetworkException e) {
                NetworkUtil.toastGenericNetworkError(LegacyHostReactivationFragment.this.getActivity());
                view.setEnabled(true);
            }
        }).execute(this.requestManager);
    }
}
