package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;

public class SmartPricingExtendedFtueActivity extends AirActivity {
    private static final String ARGS_FOR_EXTENDED_FTUE = "arg_for_extended";
    @BindView
    AirButton actionButton;
    @BindView
    TextView descText;
    @State
    boolean forExtendedFtue;
    @BindView
    ImageView imageView;
    @BindView
    TextView titleText;

    public static Intent intent(Context c, boolean forExtendedFtue2) {
        return new Intent(c, SmartPricingExtendedFtueActivity.class).putExtra(ARGS_FOR_EXTENDED_FTUE, forExtendedFtue2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_smart_pricing_ftue);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            this.forExtendedFtue = getIntent().getBooleanExtra(ARGS_FOR_EXTENDED_FTUE, false);
        }
        if (this.forExtendedFtue) {
            this.titleText.setText(C0880R.string.smart_pricing_extended_ftue_title);
            this.descText.setText(C0880R.string.smart_pricing_extended_ftue_desc);
            this.actionButton.setText(C0880R.string.smart_pricing_extended_ftue_action);
            return;
        }
        this.titleText.setText(C0880R.string.smart_pricing_fixed_price_ftue_title);
        this.descText.setText(C0880R.string.smart_pricing_fixed_price_ftue_desc);
        this.actionButton.setText(C0880R.string.smart_pricing_fixed_price_ftue_action);
        this.imageView.setVisibility(8);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onActionButtonClicked() {
        finish();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onCloseClicked() {
        finish();
    }
}
