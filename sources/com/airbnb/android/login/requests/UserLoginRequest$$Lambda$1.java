package com.airbnb.android.login.requests;

import com.airbnb.android.core.events.LoginEvent;

final /* synthetic */ class UserLoginRequest$$Lambda$1 implements Runnable {
    private final UserLoginRequest arg$1;

    private UserLoginRequest$$Lambda$1(UserLoginRequest userLoginRequest) {
        this.arg$1 = userLoginRequest;
    }

    public static Runnable lambdaFactory$(UserLoginRequest userLoginRequest) {
        return new UserLoginRequest$$Lambda$1(userLoginRequest);
    }

    public void run() {
        this.arg$1.bus.post(new LoginEvent());
    }
}
