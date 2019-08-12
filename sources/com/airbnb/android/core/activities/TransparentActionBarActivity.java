package com.airbnb.android.core.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.p027n2.components.AirToolbar;

public class TransparentActionBarActivity extends AirActivity {
    protected static final String EXTRA_BUNDLE = "bundle";
    protected static final String EXTRA_FRAGMENT_CLS = "frag_cls";
    private static final String EXTRA_REQUIRE_ACCOUNT = "require_account";
    @BindView
    AirToolbar toolbar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0716R.layout.activity_transparent_action_bar);
        ButterKnife.bind((Activity) this);
        Intent intent = getIntent();
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            showInitialFragment(Fragment.instantiate(this, intent.getStringExtra(EXTRA_FRAGMENT_CLS), intent.getBundleExtra("bundle")));
        }
    }

    public AirToolbar getAirToolbar() {
        return this.toolbar;
    }

    public void showInitialFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(C0716R.C0718id.content_container, fragment).commit();
    }

    public final void showFragment(Fragment fragment, FragmentTransitionType type, boolean addToBackStack) {
        showFragment(fragment, C0716R.C0718id.content_container, type, addToBackStack, null);
    }

    public static Intent intentForFragment(Context context, Class<?> fragCls, Bundle bundle) {
        return new Intent(context, TransparentActionBarActivity.class).putExtra(EXTRA_FRAGMENT_CLS, fragCls.getCanonicalName()).putExtra("bundle", bundle);
    }

    public static Intent intentForFragmentWithoutRequiringAccount(Context context, Class<?> fragCls, Bundle bundle) {
        return new Intent(context, TransparentActionBarActivity.class).putExtra(EXTRA_FRAGMENT_CLS, fragCls.getCanonicalName()).putExtra("bundle", bundle).putExtra(EXTRA_REQUIRE_ACCOUNT, false);
    }

    public static Intent intentForFragment(Context context, Fragment fragment) {
        return intentForFragment(context, fragment.getClass(), fragment.getArguments());
    }

    /* access modifiers changed from: protected */
    public boolean denyRequireAccountFromChild() {
        return !getIntent().getBooleanExtra(EXTRA_REQUIRE_ACCOUNT, true);
    }
}
