package com.airbnb.p027n2.browser;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager.OnBackStackChangedListener;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p002v7.app.AppCompatActivity;
import android.view.MenuItem;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.DLSComponent;
import com.airbnb.p027n2.components.DLSComponents;

/* renamed from: com.airbnb.n2.browser.DLSComponentBrowserActivity */
public class DLSComponentBrowserActivity extends AppCompatActivity {
    private final OnBackStackChangedListener backStackListener = DLSComponentBrowserActivity$$Lambda$1.lambdaFactory$(this);

    public static Intent newIntent(Context context) {
        return newIntent(context, null);
    }

    public static Intent newIntent(Context context, DLSComponent<?> component) {
        Intent intent = new Intent(context, DLSComponentBrowserActivity.class);
        if (component != null) {
            intent.putExtra("component_name", component.name());
        }
        return intent;
    }

    static /* synthetic */ void lambda$new$0(DLSComponentBrowserActivity dLSComponentBrowserActivity) {
        if (dLSComponentBrowserActivity.getSupportFragmentManager().getBackStackEntryCount() == 0) {
            dLSComponentBrowserActivity.finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        Fragment fragment;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.n2_activity_dls_component_browser);
        ButterKnife.bind((Activity) this);
        getSupportFragmentManager().addOnBackStackChangedListener(this.backStackListener);
        if (savedInstanceState == null) {
            if (getIntent().hasExtra("component_name")) {
                fragment = DLSComponentFragment.newInstance(DLSComponents.fromName(getIntent().getStringExtra("component_name")));
            } else {
                fragment = DLSComponentCategoryListFragment.newInstance();
            }
            showFragment(fragment);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        getSupportFragmentManager().removeOnBackStackChangedListener(this.backStackListener);
    }

    /* access modifiers changed from: 0000 */
    public void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            transaction.setCustomAnimations(R.anim.n2_fragment_enter, R.anim.n2_fragment_exit, R.anim.n2_fragment_enter_pop, R.anim.n2_fragment_exit_pop);
        }
        transaction.replace(R.id.container, fragment).addToBackStack(null).commit();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        onHomeActionPressed();
        return true;
    }

    private void onHomeActionPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            onBackPressed();
        }
    }
}
