package com.airbnb.android.react;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.airbnb.android.utils.AndroidVersion;

public class ReactNativeModalActivity extends ReactNativeActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.toolbar.setNavigationIcon(2);
        if (!isSuccessfullyInitialized()) {
            return;
        }
        if (AndroidVersion.isAtLeastLollipop()) {
            lollipopSetUpPostponedEnterTransition();
        } else {
            overridePendingTransition(C7663R.anim.enter_bottom, 0);
        }
    }

    @TargetApi(21)
    private void lollipopSetUpPostponedEnterTransition() {
        getWindow().setEnterTransition(makeSlideUpAnimation());
        supportPostponeEnterTransition();
        this.reactRootView.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            public boolean onPreDraw() {
                ReactNativeModalActivity.this.reactRootView.getViewTreeObserver().removeOnPreDrawListener(this);
                ReactNativeModalActivity.this.supportStartPostponedEnterTransition();
                return true;
            }
        });
    }

    public void finish() {
        super.finish();
        if (isSuccessfullyInitialized()) {
            overridePendingTransition(0, C7663R.anim.exit_bottom);
        } else {
            overridePendingTransition(0, 0);
        }
    }

    /* access modifiers changed from: protected */
    public boolean canPopPastActivity() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }
}
