package com.airbnb.android.login.p339ui;

import android.content.Context;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Login;
import com.airbnb.android.login.oauth.OAuthOption;
import com.airbnb.android.registration.models.AccountLoginData;

/* renamed from: com.airbnb.android.login.ui.BaseLoginFragment */
public abstract class BaseLoginFragment extends AirFragment {
    private LoginFragmentListener listener;

    /* renamed from: com.airbnb.android.login.ui.BaseLoginFragment$LoginFragmentListener */
    public interface LoginFragmentListener {
        void onLogInWithData(AccountLoginData accountLoginData);

        void onLogInWithOAuthOption(OAuthOption oAuthOption);

        void onMoreOptions();

        void setCurrentLoginFragment(BaseLoginFragment baseLoginFragment);

        void startLoader();

        void stopLoader();
    }

    public abstract void onLogInError(NetworkException networkException);

    public abstract void onLogInSuccess(Login login);

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (LoginFragmentListener) context;
            this.listener.setCurrentLoginFragment(this);
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement " + LoginFragmentListener.class.getSimpleName());
        }
    }

    public void onDetach() {
        super.onDetach();
        this.listener.setCurrentLoginFragment(null);
        this.listener = null;
    }

    /* access modifiers changed from: protected */
    public final void startLoader() {
        this.listener.startLoader();
    }

    /* access modifiers changed from: protected */
    public final void stopLoader() {
        this.listener.stopLoader();
    }

    /* access modifiers changed from: protected */
    public final void logInWithData(AccountLoginData loginData) {
        this.listener.onLogInWithData(loginData);
    }

    /* access modifiers changed from: protected */
    public final void logInWithOAuthOption(OAuthOption option) {
        this.listener.onLogInWithOAuthOption(option);
    }

    /* access modifiers changed from: protected */
    public final void moreOptions() {
        this.listener.onMoreOptions();
    }
}
