package com.airbnb.android.lib.tripassistant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;

public class HelpThreadActivity extends SolitAirActivity {
    private static final String EXTRA_ID = "extra_id";

    public static Intent intentForDeepLink(Context context, Bundle parameters) {
        return intentForId(context, Long.valueOf(parameters.getString("id")).longValue());
    }

    public static Intent intentForId(Context context, long id) {
        Check.argument(id > 0, "Invalid : " + id);
        return intent(context).putExtra(EXTRA_ID, id);
    }

    public static Intent intent(Context context) {
        return new Intent(context, HelpThreadActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long helpThreadId = getIntent().getLongExtra(EXTRA_ID, 0);
        if (helpThreadId == 0) {
            helpThreadId = (long) getIntent().getIntExtra(EXTRA_ID, 0);
        }
        if (helpThreadId != 0 && FeatureToggles.enableTripSupportReactNative()) {
            startActivity(ReactNativeIntents.intentForTripSupport(this, helpThreadId));
            finish();
        } else if (savedInstanceState == null) {
            showFragment(HelpThreadFragment.forId(helpThreadId), false);
        }
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C0880R.layout.activity_help_thread;
    }
}
