package com.airbnb.android.login.p339ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.login.C7331R;
import com.airbnb.android.login.oauth.OAuthLoginManager;
import com.airbnb.android.login.oauth.OAuthOption;
import com.airbnb.android.login.p339ui.views.OAuthOptionButton;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import java.util.List;

/* renamed from: com.airbnb.android.login.ui.MoreOptionsActivity */
public class MoreOptionsActivity extends AirActivity {
    public static final String EXTRA_RESULT_CREATE_ACCOUNT_SELECTED = "EXTRA_RESULT_CREATE_ACCOUNT_SELECTED";
    public static final String EXTRA_RESULT_OPTION_SELECTED = "extra_result_option_selected";
    @BindView
    ViewGroup optionsContainer;
    @BindView
    AirToolbar toolbar;

    public static Intent newIntent(Context context) {
        return new Intent(context, MoreOptionsActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C7331R.layout.activity_more_options);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        init();
    }

    private void init() {
        List<OAuthOption> options = (List) OAuthLoginManager.getOAuthLoginOptions(this).second;
        this.optionsContainer.removeAllViews();
        for (OAuthOption option : options) {
            OAuthOptionButton optionButton = (OAuthOptionButton) getLayoutInflater().inflate(C7331R.layout.oauth_option_button, this.optionsContainer, false);
            this.optionsContainer.addView(optionButton);
            optionButton.setOption(option);
            optionButton.setOnClickListener(MoreOptionsActivity$$Lambda$1.lambdaFactory$(this, option));
        }
        AirButton createAccountButton = (AirButton) getLayoutInflater().inflate(C7331R.layout.create_account_button, this.optionsContainer, false);
        this.optionsContainer.addView(createAccountButton);
        createAccountButton.setOnClickListener(MoreOptionsActivity$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$init$0(MoreOptionsActivity moreOptionsActivity, OAuthOption option, View v) {
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.CONTINUE_BUTTON, option.serviceNameForAnalytics, moreOptionsActivity.getNavigationTrackingTag());
        moreOptionsActivity.finishWithLoginOption(option);
    }

    static /* synthetic */ void lambda$init$1(MoreOptionsActivity moreOptionsActivity, View v) {
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.CREATE_ACCOUNT_BUTTON, RegistrationAnalytics.DIRECT, moreOptionsActivity.getNavigationTrackingTag());
        moreOptionsActivity.finishWithCreateAccount();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.RegistraionMoreOptions;
    }

    private void finishWithLoginOption(OAuthOption option) {
        finishWithData(new Intent().putExtra(EXTRA_RESULT_CREATE_ACCOUNT_SELECTED, false).putExtra(EXTRA_RESULT_OPTION_SELECTED, option));
    }

    private void finishWithCreateAccount() {
        finishWithData(new Intent().putExtra(EXTRA_RESULT_CREATE_ACCOUNT_SELECTED, true));
    }

    private void finishWithData(Intent data) {
        setResult(-1, data);
        finish();
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean denyRequireAccountFromChild() {
        return true;
    }
}
