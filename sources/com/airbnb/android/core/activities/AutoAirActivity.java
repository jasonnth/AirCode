package com.airbnb.android.core.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.Spannable;
import android.text.TextUtils;

public class AutoAirActivity extends SolitAirActivity {
    private static final String ARG_CLS = "frag_cls";
    public static final String EXTRA_ACTION_BAR = "action_bar";
    private static final String EXTRA_TITLE_RES = "title_res";
    private static final String EXTRA_TITLE_STRING = "title_string";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean actionBar = getIntent().getBooleanExtra(EXTRA_ACTION_BAR, true);
        CharSequence spannedTitle = getIntent().getCharSequenceExtra(EXTRA_TITLE_STRING);
        if (!actionBar) {
            this.toolbar.setVisibility(8);
        } else if (!TextUtils.isEmpty(spannedTitle)) {
            setupActionBar(spannedTitle);
        } else {
            setupActionBar(getIntent().getIntExtra(EXTRA_TITLE_RES, 0), new Object[0]);
        }
        if (savedInstanceState == null) {
            showFragment(Fragment.instantiate(this, getIntent().getStringExtra(ARG_CLS), getBundleFromIntent()), false);
        }
    }

    public static Intent intentForFragment(Context context, Class<? extends Fragment> fragCls, Bundle bundle, int titleRes) {
        Intent i = intentForBundle(context, AutoAirActivity.class, bundle);
        addArguments(i, fragCls, titleRes);
        return i;
    }

    public static void addArguments(Intent i, Class<? extends Fragment> fragCls, int titleRes) {
        i.putExtra(ARG_CLS, fragCls.getCanonicalName());
        i.putExtra(EXTRA_TITLE_RES, titleRes);
    }

    public static void addArguments(Intent i, Class<? extends Fragment> fragCls, Spannable title) {
        i.putExtra(ARG_CLS, fragCls.getCanonicalName());
        i.putExtra(EXTRA_TITLE_STRING, title);
    }

    public static Intent intentForFragment(Context context, Class<? extends Fragment> fragCls, Bundle bundle) {
        return intentForFragment(context, fragCls, bundle, 0);
    }
}
