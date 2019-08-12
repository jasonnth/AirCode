package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.lib.C0880R;
import com.airbnb.lottie.LottieAnimationView;

public class DemandBasedPricingFinishScreenActivity extends AirActivity {
    @BindView
    LottieAnimationView lottieView;

    public static Intent intent(Context c) {
        return new Intent(c, DemandBasedPricingFinishScreenActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_demand_based_pricing_finish_screen);
        ButterKnife.bind((Activity) this);
        this.lottieView.setAnimation("smart_pricing.json");
        this.lottieView.loop(true);
        this.lottieView.playAnimation();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onViewCalendarClicked() {
        startActivity(HomeActivityIntents.intentForCalendar(this));
        finish();
        Toast.makeText(this, C0880R.string.calendar_smart_pricing_toast, 1).show();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void close() {
        finish();
    }
}
