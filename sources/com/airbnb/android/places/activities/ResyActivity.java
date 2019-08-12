package com.airbnb.android.places.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.controllers.CalendarViewCallbacks;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.ResyController;
import com.airbnb.android.places.ResyController.ResyControllerProvider;
import com.airbnb.android.places.ResyState;
import com.airbnb.android.places.fragments.ResyFragment;

public class ResyActivity extends AirActivity implements CalendarViewCallbacks, ResyControllerProvider {
    private static final String INTENT_EXTRA_RESY_STATE = "resy_state";
    private static final String RESY_FRAGMENT_TAG = "fragment_resy";
    private ResyController resyController;

    public static Intent newIntent(Context context, ResyState resyState) {
        return new Intent(context, ResyActivity.class).putExtra(INTENT_EXTRA_RESY_STATE, resyState);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C7627R.layout.activity_resy);
        this.resyController = new ResyController(this, this.requestManager);
        this.resyController.onRestoreInstanceState(savedInstanceState, (ResyState) getIntent().getParcelableExtra(INTENT_EXTRA_RESY_STATE));
        if (getSupportFragmentManager().findFragmentByTag(RESY_FRAGMENT_TAG) == null) {
            showFragment(ResyFragment.newInstance(), C7627R.C7629id.content_container, FragmentTransitionType.SlideInFromSide, false, RESY_FRAGMENT_TAG);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        this.resyController.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.resyController.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public ResyController getResyController() {
        return this.resyController;
    }

    public void onCalendarDatesApplied(AirDate start, AirDate end) {
        this.resyController.updateDate(start);
        onBackPressed();
    }

    public void onStartDateClicked(AirDate start) {
    }

    public void onEndDateClicked(AirDate end) {
    }
}
