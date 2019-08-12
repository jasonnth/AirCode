package com.airbnb.android.registration;

import com.airbnb.android.core.apprater.GraphBindings;
import com.airbnb.android.registration.RegistrationComponent.Builder;
import javax.inject.Provider;

public interface RegistrationBindings extends GraphBindings {
    Provider<Builder> registrationComponentProvider();
}
