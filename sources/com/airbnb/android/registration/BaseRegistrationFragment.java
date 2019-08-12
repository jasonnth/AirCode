package com.airbnb.android.registration;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.registration.models.AccountRegistrationData;
import com.airbnb.android.utils.Strap;

public abstract class BaseRegistrationFragment extends AirFragment {
    public static final String ARG_ACCOUNT_REG_DATA = "arg_account_reg_data";
    protected AccountRegistrationData dataPassedIn;
    protected final Handler handler = new Handler();
    protected AccountRegistrationController registrationController;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.dataPassedIn = (AccountRegistrationData) getArguments().getParcelable(ARG_ACCOUNT_REG_DATA);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof AccountRegistrationController) {
            this.registrationController = (AccountRegistrationController) context;
            return;
        }
        throw new IllegalStateException("BaseRegistrationFragment must attach to an AccountRegistrationController");
    }

    public void onDetach() {
        super.onDetach();
        this.registrationController = null;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11639kv("service", this.dataPassedIn.accountSource() != null ? this.dataPassedIn.accountSource().getServiceNameForAnalytics() : RegistrationAnalytics.DIRECT);
    }
}
