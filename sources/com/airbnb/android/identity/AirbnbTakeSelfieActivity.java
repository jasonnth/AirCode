package com.airbnb.android.identity;

import android.os.Bundle;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.misnap.TakeSelfieHelpFragment;

public class AirbnbTakeSelfieActivity extends AirActivity implements TakeSelfieViewContainer {
    IdentityJitneyLogger identityJitneyLogger;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((IdentityGraph) CoreApplication.instance(this).component()).inject(this);
        setContentView(C6533R.layout.activity_take_selfie);
        getWindow().addFlags(1024);
        getWindow().addFlags(8192);
        getWindow().setBackgroundDrawable(null);
        getSupportFragmentManager().beginTransaction().disallowAddToBackStack().replace(C6533R.C6535id.content_container, new AirbnbTakeSelfieFragment()).commit();
    }

    public void showHelpContent() {
        showFragment(TakeSelfieHelpFragment.newInstance(), C6533R.C6535id.content_container, FragmentTransitionType.SlideFromBottom, true);
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public IdentityJitneyLogger getIdentityJitneyLogger() {
        return this.identityJitneyLogger;
    }
}
