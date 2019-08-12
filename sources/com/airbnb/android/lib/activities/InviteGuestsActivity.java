package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.SheetFlowActivity;
import com.airbnb.android.core.activities.SheetFlowActivity.SheetTheme;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.fragments.InviteGuestsFragment;

public class InviteGuestsActivity extends SheetFlowActivity {
    private static final String CONFIRMATION_CODE = "confirmation_code";

    public static Intent newIntent(Context context, String reservationCode) {
        return new Intent(context, InviteGuestsActivity.class).putExtra("confirmation_code", Check.notEmpty(reservationCode, "missing reservation confirmation code"));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            showFragment(InviteGuestsFragment.newInstance(getIntent().getStringExtra("confirmation_code")));
        }
    }

    public boolean useHomeAsBack() {
        return true;
    }

    public SheetTheme getDefaultTheme() {
        return SheetTheme.WHITE;
    }
}
