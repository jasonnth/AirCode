package com.airbnb.android.places.activities;

import android.app.Activity;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.models.PlaceActivity;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.fragments.PickAddToPlansFragment;

public class PickAddToPlansActivity extends AirActivity {
    private static final String FRAGMENT_TAG = "add_to_plans_fragment_tag";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, 0);
        setContentView(C7627R.layout.activity_pick_add_to_plans);
        ButterKnife.bind((Activity) this);
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        super.onResumeFragments();
        loadFragmentIfNeeded();
    }

    private void loadFragmentIfNeeded() {
        if (!hasLoadedFragment()) {
            getSupportFragmentManager().beginTransaction().replace(C7627R.C7629id.fragment_container, PickAddToPlansFragment.newInstance((PlaceActivity) getIntent().getParcelableExtra("place_activity")), FRAGMENT_TAG).commit();
        }
    }

    private boolean hasLoadedFragment() {
        return getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG) != null;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onScrimClicked() {
        finish();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }
}
