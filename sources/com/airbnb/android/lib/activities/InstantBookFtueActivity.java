package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.ManageListingAnalytics;

public class InstantBookFtueActivity extends AirActivity {
    public static Intent intent(Context c) {
        return new Intent(c, InstantBookFtueActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_ib_ftue);
        ButterKnife.bind((Activity) this);
        ManageListingAnalytics.trackInstantBookFtueImpressions();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onLearnMoreClicked() {
        ManageListingAnalytics.trackInstantBookFtuePressTry();
        setResult(-1);
        finish();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void close() {
        finish();
        ManageListingAnalytics.trackInstantBookFtuePressUp();
    }
}
