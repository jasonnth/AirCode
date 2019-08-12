package com.airbnb.android.login;

import com.airbnb.android.core.apprater.GraphBindings;
import com.airbnb.android.login.LoginComponent.Builder;
import javax.inject.Provider;

public interface LoginBindings extends GraphBindings {
    Provider<Builder> loginComponentProvider();
}
