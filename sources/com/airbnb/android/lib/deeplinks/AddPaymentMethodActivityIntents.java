package com.airbnb.android.lib.deeplinks;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;

public class AddPaymentMethodActivityIntents {
    public static Intent newIntent(Context context) {
        return new Intent(context, Activities.addPaymentMethod()).addFlags(67108864);
    }
}
