package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.models.User;
import java.util.ArrayList;

public class FiveAxiomIntents {
    public static final String EXTRA_FLOW = "extra_flow";
    public static final String EXTRA_HOST = "extra_host";
    public static final String EXTRA_STEPS = "extra_steps";

    public static Intent newIntent(Context context, ArrayList<AccountVerificationStep> steps, VerificationFlow flow, User host) {
        return new Intent(context, Activities.fiveAxiom()).putExtra(EXTRA_FLOW, flow).putExtra(EXTRA_STEPS, steps).putExtra("extra_host", host);
    }
}
