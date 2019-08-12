package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import com.airbnb.android.core.activities.AutoAirActivity;

public class AutoAirModalLargeActivity extends AutoAirActivity {
    public static Intent intentForFragment(Context context, Class fragCls, Bundle bundle, int titleRes) {
        Intent i = intentForBundle(context, AutoAirModalLargeActivity.class, bundle);
        addArguments(i, fragCls, titleRes);
        return i;
    }

    public static Intent intentForFragment(Context context, Class fragCls, Bundle bundle, Spannable title) {
        Intent i = intentForBundle(context, AutoAirModalLargeActivity.class, bundle);
        addArguments(i, fragCls, title);
        return i;
    }

    public static Intent intentForFragment(Context context, Class fragCls, Bundle bundle) {
        return AutoAirActivity.intentForFragment(context, fragCls, bundle, 0);
    }
}
