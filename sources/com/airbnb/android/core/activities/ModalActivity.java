package com.airbnb.android.core.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.interfaces.OnBackListener;

public class ModalActivity extends AirActivity {
    private static final String ARG_BUNDLE = "bundle";
    private static final String ARG_CLS = "frag_cls";
    private OnBackListener mOnBackListener;

    public static Intent intentForFragment(Context context, Fragment modalFragment) {
        return intentForFragment(ModalActivity.class, context, modalFragment);
    }

    public static Intent intentForFragment(Class<?> clazz, Context context, Fragment modalFragment) {
        return new Intent(context, clazz).putExtra(ARG_CLS, modalFragment.getClass().getCanonicalName()).putExtra("bundle", modalFragment.getArguments());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0716R.layout.activity_simple_fragment);
        FragmentTransitionType type = FragmentTransitionType.SlideFromBottom;
        overridePendingTransition(type.enter, type.exit);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(C0716R.C0718id.content_container, Fragment.instantiate(this, getIntent().getStringExtra(ARG_CLS), getBundleFromIntent())).commit();
        }
    }

    public void finish() {
        super.finish();
        FragmentTransitionType type = FragmentTransitionType.SlideFromBottom;
        overridePendingTransition(type.popEnter, type.popExit);
    }

    public Bundle getBundleFromIntent() {
        return getIntent() != null ? getIntent().getBundleExtra("bundle") : new Bundle(0);
    }

    public void onBackPressed() {
        if (this.mOnBackListener == null || !this.mOnBackListener.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public void setOnBackPressedListener(OnBackListener listener) {
        this.mOnBackListener = listener;
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }
}
