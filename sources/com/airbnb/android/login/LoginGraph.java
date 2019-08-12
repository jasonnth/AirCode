package com.airbnb.android.login;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.login.p339ui.LoginActivity;
import com.airbnb.android.login.requests.UserLoginRequest;

public interface LoginGraph extends BaseGraph {
    void inject(UserLoginRequest userLoginRequest);

    void inject(LoginActivity loginActivity);
}
