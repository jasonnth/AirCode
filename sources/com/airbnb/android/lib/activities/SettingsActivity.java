package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.lib.fragments.AccountSettingsFragment;

public class SettingsActivity extends AirActivity {
    public static Intent intentForDefault(Context context) {
        return new Intent(context, SettingsActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        if (MiscUtils.isUserAMonkey()) {
            finish();
            return;
        }
        super.onCreate(savedInstanceState);
        setContentView(C5658R.layout.activity_single_fragment_layout);
        if (savedInstanceState == null) {
            showFragment(AccountSettingsFragment.newInstance());
        }
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public void showFragment(Fragment fragment) {
        showFragment(fragment, C5658R.C5660id.root_container, FragmentTransitionType.SlideInFromSide, true, fragment.getClass().getCanonicalName());
    }
}
