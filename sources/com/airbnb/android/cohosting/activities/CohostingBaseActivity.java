package com.airbnb.android.cohosting.activities;

import android.support.p000v4.app.Fragment;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;

public class CohostingBaseActivity extends AirActivity {
    public void showModal(Fragment fragment) {
        showModal(fragment, C5658R.C5660id.content_container, C5658R.C5660id.modal_container, true, fragment.getClass().getCanonicalName());
    }

    public void showFragment(Fragment fragment) {
        showFragment(fragment, C5658R.C5660id.content_container, FragmentTransitionType.SlideInFromSide, true, fragment.getClass().getCanonicalName());
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }
}
