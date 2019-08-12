package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.airbnb.android.contentframework.ContentFrameworkDeepLinkModuleLoader;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreDeepLinkModuleLoader;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.controllers.SplashScreenController;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.erf.PricingFeatureToggles;
import com.airbnb.android.core.intents.EntryActivityIntents;
import com.airbnb.android.core.intents.LoginActivityIntents;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.ColdStartExperimentDeliverer;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.explore.ExploreDeepLinkModuleLoader;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.deeplinks.AirbnbDeepLinkModuleLoader;
import com.airbnb.android.lib.utils.webintent.WebIntentDispatch;
import com.airbnb.android.payout.PayoutDeepLinkModuleLoader;
import com.airbnb.android.react.ReactDeepLinkRegistry;
import com.airbnb.android.registration.RegistrationDeepLinkModuleLoader;
import com.airbnb.android.utils.ConcurrentUtil;
import com.airbnb.android.utils.Strap;
import com.facebook.common.util.UriUtil;
import dagger.Lazy;

public class EntryActivity extends Activity {
    private static final int RC_SIGN_IN = 141;
    private static final int RC_SPLASH_SCREEN = 140;
    AirbnbAccountManager accountManager;
    AppLaunchUtils appLaunchUtils;
    DebugSettings debugSettings;
    AppLaunchAnalytics launchAnalytics;
    Lazy<ReactDeepLinkRegistry> reactDeepLinkRegistry;
    SplashScreenController splashScreenController;

    enum State {
        Start,
        SplashScreenShown,
        UserLoggedIn
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (shouldFinishDirectly(getIntent())) {
            finish();
            return;
        }
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        if (!ColdStartExperimentDeliverer.isInTreatmentGroup()) {
            this.reactDeepLinkRegistry.get();
        }
        if (savedInstanceState == null) {
            setState(State.Start);
            if (ColdStartExperimentDeliverer.isInTreatmentGroup()) {
                this.appLaunchUtils.doAppStartupStuffDeferred(this);
            } else {
                this.appLaunchUtils.doAppStartupStuff(this);
            }
            this.appLaunchUtils.doTrack(this, getIntent());
            ConcurrentUtil.deferParallel(EntryActivity$$Lambda$1.lambdaFactory$(this, System.currentTimeMillis()));
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            finish();
            return;
        }
        switch (requestCode) {
            case 140:
                setState(State.SplashScreenShown);
                return;
            case 141:
                if (!this.accountManager.isCurrentUserAuthorized()) {
                    BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("User is supposed to be signed in but is not!"));
                    finish();
                    return;
                }
                setState(State.UserLoggedIn);
                return;
            default:
                return;
        }
    }

    private void setState(State newState) {
        switch (newState) {
            case Start:
                if (!this.splashScreenController.shouldShowSplashScreen()) {
                    setState(State.SplashScreenShown);
                    return;
                } else {
                    startActivityForResult(SplashScreenActivity.newIntent(this), 140);
                    return;
                }
            case SplashScreenShown:
                if (this.accountManager.isCurrentUserAuthorized()) {
                    setState(State.UserLoggedIn);
                    return;
                } else {
                    launchLoginActivityIfNecessary();
                    return;
                }
            case UserLoggedIn:
                enterApp();
                return;
            default:
                return;
        }
    }

    private void launchLoginActivityIfNecessary() {
        Intent newIntent = new Intent(getIntent());
        newIntent.setFlags(0);
        if (!isWebLinkIntent(newIntent) || !WebIntentDispatch.isWebIntentInLoggedOutWhiteList(newIntent.getData().getPath())) {
            startActivityForResult(LoginActivityIntents.intent(this), 141);
            return;
        }
        newIntent.setComponent(new ComponentName(this, WebIntentDispatch.class));
        startActivity(newIntent);
        finish();
    }

    private void enterApp() {
        Intent originalIntent = getIntent();
        Intent newIntent = new Intent(originalIntent);
        newIntent.setFlags(0);
        if (originalIntent.getExtras() == null || !originalIntent.getExtras().containsKey(EntryActivityIntents.EXTRA_INTENT_TO_LAUNCH)) {
            Uri data = originalIntent.getData();
            if (SearchIntentActivity.isSearchAction(originalIntent)) {
                logSearchActionIntent(originalIntent);
                newIntent.setComponent(new ComponentName(this, SearchIntentActivity.class));
            } else if (isWebLinkIntent(originalIntent)) {
                newIntent.setComponent(new ComponentName(this, WebIntentDispatch.class));
            } else if (DeepLinkUtils.isDeepLink(data)) {
                String reactSceneName = data == null ? null : data.toString();
                if (((ReactDeepLinkRegistry) this.reactDeepLinkRegistry.get()).canHandle(reactSceneName)) {
                    ((ReactDeepLinkRegistry) this.reactDeepLinkRegistry.get()).dispatch(this, reactSceneName);
                } else {
                    new DeepLinkDelegate(new AirbnbDeepLinkModuleLoader(), new CoreDeepLinkModuleLoader(), new ExploreDeepLinkModuleLoader(), new RegistrationDeepLinkModuleLoader(), new ContentFrameworkDeepLinkModuleLoader(), new PayoutDeepLinkModuleLoader()).dispatchFrom(this);
                }
                finish();
                return;
            } else {
                newIntent = HomeActivityIntents.intentForDefaultTab(this);
            }
        } else {
            newIntent = (Intent) originalIntent.getParcelableExtra(EntryActivityIntents.EXTRA_INTENT_TO_LAUNCH);
        }
        startActivity(newIntent);
        finish();
    }

    private static boolean shouldFinishDirectly(Intent intent) {
        return "android.intent.action.MAIN".equals(intent.getAction()) && (intent.getFlags() & 4194304) != 0;
    }

    private boolean isWebLinkIntent(Intent intent) {
        DebugSettings debugSettings2 = this.debugSettings;
        if (DebugSettings.FORCE_DEEPLINK.isEnabled() || PricingFeatureToggles.useDLDPricingScreens(intent.toUri(0))) {
            return false;
        }
        if (UriUtil.HTTPS_SCHEME.equals(intent.getScheme()) || UriUtil.HTTP_SCHEME.equals(intent.getScheme())) {
            return true;
        }
        return false;
    }

    private static void logSearchActionIntent(Intent intent) {
        AirbnbEventLogger.track(AirbnbEventLogger.EVENT_ENGINEERING_LOG_2, Strap.make().mo11639kv(BaseAnalytics.OPERATION, "search_action_tracking").mo11639kv("action", intent.getAction()));
    }
}
