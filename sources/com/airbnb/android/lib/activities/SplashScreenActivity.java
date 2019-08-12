package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.controllers.ExperimentConfigController;
import com.airbnb.android.core.controllers.SplashScreenController;
import com.airbnb.android.core.events.ExperimentConfigFetchCompleteEvent;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.Strap;
import com.squareup.otto.Subscribe;
import icepick.State;

public class SplashScreenActivity extends AirActivity {
    private static final int CHINA_LAUNCH_SPLASH_MS = 3000;
    private static final int SPLASH_SCREEN_TIMEOUT_MS = 10000;
    @BindView
    FrameLayout cblSplashScreen;
    private final Runnable chinaLaunchImageShownComplete = new Runnable() {
        public void run() {
            SplashScreenActivity.this.cblSplashScreen.setVisibility(8);
            SplashScreenActivity.this.loaderFrame.startAnimation();
        }
    };
    ExperimentConfigController experimentConfigController;
    private final Handler handler = new Handler();
    @BindView
    LoaderFrame loaderFrame;
    @State
    long onCreateTimeNano;
    SplashScreenController splashScreenController;
    private final Runnable timeoutRunnable = SplashScreenActivity$$Lambda$1.lambdaFactory$(this);

    enum FinishState {
        Timeout("splash_screen_timeout", false),
        Success("splash_screen_finish", true),
        Fail("splash_screen_finish", false);
        
        final boolean fetchSuccess;
        final String operationName;

        private FinishState(String operationName2, boolean fetchSuccess2) {
            this.operationName = operationName2;
            this.fetchSuccess = fetchSuccess2;
        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, SplashScreenActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.bus.register(this);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        setContentView(C0880R.layout.activity_splash_screen);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            this.onCreateTimeNano = System.nanoTime();
            track("splash_screen_show", null);
        }
        this.handler.postDelayed(this.timeoutRunnable, 10000);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        fetchExperimentConfig();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.bus.unregister(this);
        this.handler.removeCallbacksAndMessages(null);
    }

    /* access modifiers changed from: protected */
    public boolean denyRequireAccountFromChild() {
        return true;
    }

    @Subscribe
    public void onExperimentConfigFetchComplete(ExperimentConfigFetchCompleteEvent event) {
        if (!isFinishing()) {
            finish(event.success ? FinishState.Success : FinishState.Fail);
        }
    }

    private void fetchExperimentConfig() {
        if (ChinaUtils.shouldShowSplashScreenForCBL()) {
            this.cblSplashScreen.setVisibility(0);
            this.handler.postDelayed(this.chinaLaunchImageShownComplete, 3000);
        } else {
            this.loaderFrame.startAnimation();
        }
        this.experimentConfigController.fetchConfigurationForUser(this.accountManager.getCurrentUserId());
    }

    /* access modifiers changed from: private */
    public void finish(FinishState finishState) {
        this.handler.removeCallbacksAndMessages(null);
        this.loaderFrame.finishImmediate();
        this.splashScreenController.onSplashScreenFinished(finishState.fetchSuccess);
        track(finishState.operationName, Strap.make().mo11640kv("success", finishState.fetchSuccess).mo11638kv("display_duration_ms", (System.nanoTime() - this.onCreateTimeNano) / 1000000));
        setResult(-1);
        finish();
    }

    private void track(String operation, Strap metadata) {
        AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv(BaseAnalytics.OPERATION, operation).mix(metadata));
    }
}
