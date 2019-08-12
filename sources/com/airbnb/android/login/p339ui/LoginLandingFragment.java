package com.airbnb.android.login.p339ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.CountriesRequest;
import com.airbnb.android.core.responses.CountriesResponse;
import com.airbnb.android.login.C7331R;
import com.airbnb.android.login.oauth.OAuthLoginManager;
import com.airbnb.android.login.oauth.OAuthOption;
import com.airbnb.android.login.p339ui.views.OAuthOptionButton;
import com.airbnb.android.login.utils.LoginUtils;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.p027n2.primitives.AirTextView;
import java.util.List;
import p032rx.Observer;

/* renamed from: com.airbnb.android.login.ui.LoginLandingFragment */
public class LoginLandingFragment extends AirFragment {
    private static final String ARG_PRIMARY_OPTION = "arg_primary_option";
    @BindView
    TextView agreementText;
    private LoginLandingFragmentListener callback;
    final RequestListener<CountriesResponse> countriesResponseRequestListener = new C0699RL().onResponse(LoginLandingFragment$$Lambda$1.lambdaFactory$(this)).build();
    @BindView
    AirTextView loginLandingHeader;
    private OAuthOption primaryOption;
    @BindView
    OAuthOptionButton primaryOptionButton;

    /* renamed from: com.airbnb.android.login.ui.LoginLandingFragment$LoginLandingFragmentListener */
    public interface LoginLandingFragmentListener {
        void onLogInWithEmail();

        void onLoginWithPrimaryOption(OAuthOption oAuthOption);

        void onMoreOptions();

        void onSignUp();
    }

    public static LoginLandingFragment newInstance() {
        return newInstance(null);
    }

    public static LoginLandingFragment newInstance(String lastUsedLogin) {
        Bundle args = new Bundle();
        args.putString(ARG_PRIMARY_OPTION, lastUsedLogin);
        LoginLandingFragment fragment = new LoginLandingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    static /* synthetic */ void lambda$new$0(LoginLandingFragment loginLandingFragment, CountriesResponse data) {
        loginLandingFragment.mPreferences.getSharedPreferences().edit().putString(AirbnbConstants.PREFS_COUNTRY_OF_CURRENT_IP, data.getFirstCountryCode()).apply();
        LoginUtils.setupTOSText(loginLandingFragment.agreementText, loginLandingFragment.getActivity());
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z = false;
        View view = inflater.inflate(C7331R.layout.fragment_login_landing, container, false);
        bindViews(view);
        setupLoginOptions();
        if (savedInstanceState == null) {
            z = true;
        }
        setUpTOSTexts(z);
        this.loginLandingHeader.setText(this.resourceManager.getString(C7331R.string.signin_landing_header));
        return view;
    }

    public void onDestroyView() {
        this.agreementText.setText("");
        super.onDestroyView();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C7331R.C7334menu.menu_login, menu);
        MenuItem item = menu.findItem(C7331R.C7333id.log_in);
        if (menu != null) {
            item.setTitle(this.resourceManager.getString(C7331R.string.sign_in));
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C7331R.C7333id.log_in) {
            return false;
        }
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.LOG_IN_BUTTON, NavigationTag.RegistrationLanding);
        this.callback.onLogInWithEmail();
        return true;
    }

    @OnClick
    public void onCreateAccountSelected() {
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.CREATE_ACCOUNT_BUTTON, RegistrationAnalytics.DIRECT, NavigationTag.RegistrationLanding);
        this.callback.onSignUp();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onPrimaryLoginOptionClick() {
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.CONTINUE_BUTTON, this.primaryOption.serviceNameForAnalytics, NavigationTag.RegistrationLanding);
        this.callback.onLoginWithPrimaryOption(this.primaryOption);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onMoreOptionsClick() {
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.MORE_OPTIONS_BUTTON, NavigationTag.RegistrationLanding);
        this.callback.onMoreOptions();
    }

    private void setupLoginOptions() {
        Pair<OAuthOption, List<OAuthOption>> signInOptionConfig = OAuthLoginManager.getOAuthLoginOptions(getContext());
        String primaryOptionArg = getArguments().getString(ARG_PRIMARY_OPTION);
        if (primaryOptionArg == null || OAuthOption.stringToOAuthOption(primaryOptionArg) == null) {
            this.primaryOption = (OAuthOption) signInOptionConfig.first;
        } else {
            this.primaryOption = OAuthOption.stringToOAuthOption(primaryOptionArg);
        }
        this.primaryOptionButton.setOptionAsPrimary(this.primaryOption);
    }

    private void setUpTOSTexts(boolean needFetchIp) {
        LoginUtils.setupTOSText(this.agreementText, getActivity());
        if (needFetchIp) {
            CountriesRequest.forCountryOfIP().withListener((Observer) this.countriesResponseRequestListener).execute(this.requestManager);
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.callback = (LoginLandingFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement " + LoginLandingFragmentListener.class.getSimpleName());
        }
    }

    public void onDetach() {
        super.onDetach();
        this.callback = null;
    }
}
