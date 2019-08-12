package com.airbnb.android.core.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.utils.ExtendableBundleBuilder;
import icepick.State;

public class AutoFragmentActivity extends AirActivity {
    private static final String EXTRA_ALLOW_ACCESS_WITHOUT_SESSION = "allowAccessWithoutSession";
    private static final String EXTRA_ARGUMENTS = "bundle";
    private static final String EXTRA_FRAGMENT_CLASS = "fragment_class";
    @State
    boolean allowAccessWithoutSession;

    public static class Builder extends ExtendableBundleBuilder<Builder> {
        private boolean allowAccessWithoutSession;
        private final Context context;
        private final Class<? extends Fragment> fragmentClass;

        private Builder(Context context2, Class<? extends Fragment> fragmentClass2) {
            this.context = context2;
            this.fragmentClass = fragmentClass2;
        }

        public Builder allowAccessWithoutSession() {
            this.allowAccessWithoutSession = true;
            return this;
        }

        public Intent build() {
            return AutoFragmentActivity.createIntent(this.context, this.fragmentClass, toBundle(), this.allowAccessWithoutSession);
        }
    }

    public static Builder create(Context context, Class<? extends Fragment> fragmentClass) {
        return new Builder(context, fragmentClass);
    }

    /* access modifiers changed from: private */
    public static Intent createIntent(Context context, Class<? extends Fragment> fragmentClass, Bundle arguments, boolean allowAccessWithoutSession2) {
        return new Intent(context, AutoFragmentActivity.class).putExtra(EXTRA_FRAGMENT_CLASS, fragmentClass.getCanonicalName()).putExtra("bundle", arguments).putExtra(EXTRA_ALLOW_ACCESS_WITHOUT_SESSION, allowAccessWithoutSession2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0716R.layout.activity_auto_fragment);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            String fragmentClass = intent.getStringExtra(EXTRA_FRAGMENT_CLASS);
            Bundle bundle = intent.getBundleExtra("bundle");
            this.allowAccessWithoutSession = intent.getBooleanExtra(EXTRA_ALLOW_ACCESS_WITHOUT_SESSION, false);
            getSupportFragmentManager().beginTransaction().replace(C0716R.C0718id.content_container, Fragment.instantiate(this, fragmentClass, bundle)).commit();
        }
    }

    /* access modifiers changed from: protected */
    public boolean denyRequireAccountFromChild() {
        return this.allowAccessWithoutSession;
    }
}
