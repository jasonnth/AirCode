package com.airbnb.android.lib.businesstravel;

import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.intents.BusinessTravelIntents;
import com.airbnb.android.lib.C0880R;

public class BusinessTravelDeepLinkActivity extends AirActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            showFragment(BusinessTravelWelcomeFragment.instanceForWorkEmail(getIntent().getStringExtra(BusinessTravelIntents.EXTRA_WORK_EMAIL)), C0880R.C0882id.content_container, FragmentTransitionType.SlideFromBottom, false);
        }
    }
}
