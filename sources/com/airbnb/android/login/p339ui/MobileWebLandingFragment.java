package com.airbnb.android.login.p339ui;

import android.content.Context;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.BindView;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.fragments.NavigationLoggingElement;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.core.models.Login;
import com.airbnb.android.login.C7331R;
import com.airbnb.android.registration.models.AccountLoginData;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.HeroMarquee;
import com.airbnb.p027n2.utils.SnackbarWrapper;

/* renamed from: com.airbnb.android.login.ui.MobileWebLandingFragment */
public class MobileWebLandingFragment extends BaseLoginFragment implements NavigationLoggingElement {
    public static final String ARG_ID = "user_id";
    public static final String ARG_NAME = "name";
    public static final String ARG_TOKEN = "token";
    public static final String TAG = MobileWebLandingFragment.class.getSimpleName();
    @BindView
    FrameLayout container;
    private MobileWebLandingFragmentListener listener;
    @BindView
    HeroMarquee marquee;

    /* renamed from: com.airbnb.android.login.ui.MobileWebLandingFragment$MobileWebLandingFragmentListener */
    public interface MobileWebLandingFragmentListener {
        void switchAccount();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container2, Bundle savedInstanceState) {
        View view = inflater.inflate(C7331R.layout.fragment_mobile_web_landing, container2, false);
        bindViews(view);
        String mowebToken = getArguments().getString("token");
        String id = getArguments().getString("user_id");
        String name = getArguments().getString("name");
        this.marquee.setFirstButtonText((CharSequence) getString(C7331R.string.moweb_native_landing_first_button, name));
        this.marquee.setFirstButtonTextColor(ContextCompat.getColor(this.marquee.getContext(), C7331R.color.n2_babu));
        this.marquee.setFirstButtonClickListener(MobileWebLandingFragment$$Lambda$1.lambdaFactory$(this, mowebToken, id));
        this.marquee.setSecondButtonClickListener(MobileWebLandingFragment$$Lambda$2.lambdaFactory$(this));
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.navigationAnalytics.trackImpression(this);
    }

    public static MobileWebLandingFragment newInstance(String token, String id, String name) {
        return (MobileWebLandingFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new MobileWebLandingFragment()).putString("token", token)).putString("user_id", id)).putString("name", name)).build();
    }

    /* access modifiers changed from: private */
    public void attemptLogin(String token, String id) {
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.MOWEB_LANDING_CONTINUE_BUTTON, NavigationTag.MobileWebAutoAuthentication);
        logInWithData(AccountLoginData.builder(AccountSource.MoWeb).mowebAccessToken(token).mowebAuthId(id).build());
    }

    private void showErrorResponse() {
        new SnackbarWrapper().view(this.container).body(C7331R.string.moweb_native_landing_login_failed).duration(0).buildAndShow();
    }

    /* access modifiers changed from: private */
    public void switchAccount() {
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.MOWEB_LANDING_SWITCH_ACCOUNT_BUTTON, NavigationTag.MobileWebAutoAuthentication);
        this.listener.switchAccount();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.MobileWebAutoAuthentication;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (MobileWebLandingFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement " + MobileWebLandingFragmentListener.class.getSimpleName());
        }
    }

    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

    public void onLogInSuccess(Login login) {
        RegistrationAnalytics.trackRequestResponseSuccess(RegistrationAnalytics.MOWEB_AUTO_AUTH_SIGN_IN_RESPONSE, RegistrationAnalytics.MOWEB, getNavigationTrackingTag(), getContext());
    }

    public void onLogInError(NetworkException exception) {
        RegistrationAnalytics.trackRequestResponseFailure(RegistrationAnalytics.MOWEB_AUTO_AUTH_SIGN_IN_RESPONSE, RegistrationAnalytics.MOWEB, getNavigationTrackingTag(), exception);
        showErrorResponse();
    }
}
