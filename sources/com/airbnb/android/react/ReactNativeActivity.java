package com.airbnb.android.react;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.content.ContextCompat;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;
import butterknife.ButterKnife;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.intents.EntryActivityIntents;
import com.airbnb.android.core.react.AirReactInstanceManager;
import com.airbnb.android.core.utils.MemoryUtils;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.utils.ReactNativeIntentUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.animation.SimpleTransitionListener;
import com.airbnb.jitney.event.logging.NativeMeasurementType.p159v1.C2445NativeMeasurementType;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.transitions.AutoSharedElementCallback;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.PermissionAwareActivity;
import com.facebook.react.modules.core.PermissionListener;
import com.google.common.collect.ImmutableMap.Builder;
import icepick.State;
import java.util.List;
import java.util.Locale;

public class ReactNativeActivity extends AirActivity implements ReactInterface, DefaultHardwareBackBtnHandler, PermissionAwareActivity {
    private static final String AIRBNB_INSTANCE_ID_PROP = "airbnbInstanceId";
    private static final int FAKE_ENTER_TRANSITION_TIME_IN_MS = 500;
    private static final String ON_APPEAR = "onAppear";
    private static final String ON_BUTTON_PRESS = "onButtonPress";
    private static final String ON_DISAPPEAR = "onDisappear";
    private static final String ON_ENTER_TRANSITION_COMPLETE = "onEnterTransitionComplete";
    private static final String ON_LEFT_PRESS = "onLeftPress";
    private static final String ON_LINK_PRESS = "onLinkPress";
    private static final int RENDER_TIMEOUT_IN_MS = 700;
    private static final int SHARED_ELEMENT_TARGET_API = 22;
    private static final String TAG = "ReactNativeActivity";
    private static int UUID = 1;
    private static final int WAITING_TRANSITION_TARGET_API = 21;
    private ReactInterfaceManager activityManager;
    AirbnbApi airbnbApi;
    @State
    String instanceId;
    private boolean isSharedElementTransition = false;
    private boolean isWaitingForRenderToFinish = false;
    private String link;
    LoggingContextFactory loggingContextFactory;
    private List<MenuButton> menuButtons;
    @State
    String navigationTag;
    private PerformanceLogger performanceLogger;
    @State
    String performanceLoggerEventName;
    private PermissionListener permissionListener;
    AirReactInstanceManager reactInstanceManager;
    ReactNavigationCoordinator reactNavigationCoordinator;
    ReactRootView reactRootView;
    SharedPrefsHelper sharedPrefsHelper;
    AirToolbar toolbar;
    private final Handler transitionHandler = new Handler();

    public static Intent intentWithDismissFlag() {
        return new Intent().putExtra("isDismiss", true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ReactGraph) CoreApplication.instance(this).component()).inject(this);
        this.performanceLogger = new PerformanceLogger(this.loggingContextFactory, this.sharedPrefsHelper);
        String moduleName = getIntent().getStringExtra(ReactNativeIntentUtils.REACT_MODULE_NAME);
        this.performanceLoggerEventName = String.format("rn_first_render_%s", new Object[]{moduleName});
        this.navigationTag = getIntent().getStringExtra(ReactNativeIntentUtils.REACT_NAVIGATION_TAG);
        this.performanceLogger.markStart(this.performanceLoggerEventName, new Builder().put("react_native", Boolean.toString(true)).put("initialized", Boolean.toString(isSuccessfullyInitialized())).put("has_shared_element_transition", Boolean.toString(ReactNativeUtils.hasSharedElementTransition(getIntent()))).put("memory_level", Integer.toString(MemoryUtils.getLastTrimLevel())).build(), null);
        if (this.reactNavigationCoordinator.getBackgroundColorForModuleName(moduleName) == 0) {
            setTheme(C7663R.C7668style.Theme_Airbnb_ReactTranslucent);
        }
        setContentView(C7663R.layout.activity_react_native);
        this.toolbar = (AirToolbar) ButterKnife.findById((Activity) this, C7663R.C7665id.toolbar);
        setToolbar(this.toolbar);
        if (!isSuccessfullyInitialized()) {
            this.reactInstanceManager.addReactInstanceEventListener(ReactNativeActivity$$Lambda$1.lambdaFactory$(this));
        } else {
            onCreateWithReactContext();
            setupTransition();
        }
        this.reactInstanceManager.signalWaitingForFirstRender(this);
    }

    /* access modifiers changed from: private */
    public void onCreateWithReactContext() {
        if (!supportIsDestroyed()) {
            ButterKnife.findById((Activity) this, C7663R.C7665id.loading_view).setVisibility(8);
            if (!isSuccessfullyInitialized()) {
                ReactNativeUtils.showAlertBecauseChecksFailed(this, ReactNativeActivity$$Lambda$2.lambdaFactory$(this));
                return;
            }
            String moduleName = getIntent().getStringExtra(ReactNativeIntentUtils.REACT_MODULE_NAME);
            this.activityManager = new ReactInterfaceManager(this);
            if (this.instanceId == null) {
                int i = UUID;
                UUID = i + 1;
                this.instanceId = String.format(Locale.ENGLISH, "%1s_%2$d", new Object[]{moduleName, Integer.valueOf(i)});
            }
            this.reactNavigationCoordinator.registerComponent(this, this.instanceId);
            this.toolbar.setTheme(this.reactNavigationCoordinator.getToolbarThemeForModuleName(moduleName));
            Integer toolbarBgColor = this.reactNavigationCoordinator.getToolbarBackgroundColorForModuleName(moduleName);
            if (toolbarBgColor != null) {
                this.toolbar.setBackgroundColor(ContextCompat.getColor(this, toolbarBgColor.intValue()));
            }
            Integer toolbarFgColor = this.reactNavigationCoordinator.getToolbarForegroundColorForModuleName(moduleName);
            if (toolbarFgColor != null) {
                this.toolbar.setForegroundColor(ContextCompat.getColor(this, toolbarFgColor.intValue()));
            }
            if (this.reactRootView == null) {
                ViewStub reactViewStub = (ViewStub) ButterKnife.findById((Activity) this, C7663R.C7665id.react_root_view_stub);
                reactViewStub.setLayoutResource(C7663R.layout.view_holder_react_root_view);
                this.reactRootView = (ReactRootView) reactViewStub.inflate();
            }
            Bundle props = getIntent().getBundleExtra(ReactNativeIntentUtils.REACT_PROPS);
            if (props == null) {
                props = new Bundle();
            }
            props.putString(AIRBNB_INSTANCE_ID_PROP, this.instanceId);
            this.reactRootView.setBackgroundColor(this.reactNavigationCoordinator.getBackgroundColorForModuleName(moduleName));
            this.reactInstanceManager.startReactApplication(this.reactRootView, moduleName, props);
        }
    }

    private void setupTransition() {
        if (VERSION.SDK_INT >= 22 && ReactNativeUtils.hasSharedElementTransition(getIntent())) {
            setupSharedElementTransition();
        } else if (!isSuccessfullyInitialized() || VERSION.SDK_INT < 21) {
            this.transitionHandler.postDelayed(ReactNativeActivity$$Lambda$3.lambdaFactory$(this), 500);
        } else {
            setupDefaultWaitingForRenderTransition();
        }
        this.isWaitingForRenderToFinish = true;
        this.transitionHandler.postDelayed(ReactNativeActivity$$Lambda$4.lambdaFactory$(this), 700);
    }

    @TargetApi(22)
    private void setupSharedElementTransition() {
        this.isSharedElementTransition = true;
        supportPostponeEnterTransition();
        setEnterSharedElementCallback(new AutoSharedElementCallback(this));
        attachEnterTransitionListener(getWindow().getEnterTransition());
    }

    @TargetApi(21)
    private void attachEnterTransitionListener(Transition transition) {
        transition.addListener(new SimpleTransitionListener() {
            public void onTransitionEnd(Transition transition) {
                ReactNativeActivity.this.emitEvent(ReactNativeActivity.ON_ENTER_TRANSITION_COMPLETE, null);
            }
        });
    }

    @TargetApi(21)
    private void setEnterTransition(Transition transition) {
        attachEnterTransitionListener(transition);
        getWindow().setEnterTransition(transition);
    }

    @TargetApi(21)
    private void setupDefaultWaitingForRenderTransition() {
        supportPostponeEnterTransition();
        setEnterTransition(makeSlideLeftAnimation());
    }

    public void signalFirstRenderComplete() {
        if (!this.isSharedElementTransition && this.isWaitingForRenderToFinish) {
            this.transitionHandler.post(ReactNativeActivity$$Lambda$5.lambdaFactory$(this));
        }
        this.reactInstanceManager.signalFirstRenderComplete(this);
    }

    public void notifySharedElementAddition() {
        if (this.isWaitingForRenderToFinish) {
            this.transitionHandler.removeCallbacksAndMessages(null);
            this.transitionHandler.post(ReactNativeActivity$$Lambda$6.lambdaFactory$(this));
        }
    }

    /* access modifiers changed from: private */
    public void onFinishWaitingForRender(boolean timedOut) {
        if (this.isWaitingForRenderToFinish && !supportIsDestroyed()) {
            this.isWaitingForRenderToFinish = false;
            supportStartPostponedEnterTransition();
            this.performanceLogger.markCompleted(this.performanceLoggerEventName, new Builder().put("timed_out", Boolean.toString(timedOut)).build(), C2445NativeMeasurementType.FullPageLoadTime, null);
        }
    }

    public String getInstanceId() {
        return this.instanceId;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.toolbar != null) {
            this.toolbar.onCreateOptionsMenu(0, menu, getMenuInflater());
            createOptionsMenu(menu);
        }
        return true;
    }

    private void createOptionsMenu(Menu menu) {
        if (this.link != null) {
            menu.add(this.link).setShowAsAction(1);
        } else if (this.menuButtons != null) {
            NavigatorModule.addButtonsToMenu(this, menu, this.menuButtons, ReactNativeActivity$$Lambda$7.lambdaFactory$(this));
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            emitEvent(ON_LEFT_PRESS, null);
            if (!this.reactNavigationCoordinator.getDismissCloseBehavior(this)) {
                return super.onOptionsItemSelected(item);
            }
            dismiss();
            return true;
        }
        emitEvent(ON_LINK_PRESS, null);
        return false;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean popInclusive = false;
        super.onActivityResult(requestCode, resultCode, data);
        String popNavigationTag = data == null ? null : data.getStringExtra(ReactNativeIntentUtils.REACT_NAVIGATION_TAG);
        if (data != null && data.getBooleanExtra("popInclusive", false)) {
            popInclusive = true;
        }
        if (!canPopPastActivity() || popNavigationTag == null) {
            if (this.activityManager != null) {
                this.activityManager.onActivityResult(requestCode, resultCode, data);
            }
        } else if (!popNavigationTag.equals(this.navigationTag)) {
            Intent intent = new Intent();
            intent.putExtra(ReactNativeIntentUtils.REACT_NAVIGATION_TAG, popNavigationTag);
            intent.putExtra("popInclusive", popInclusive);
            setResult(-1, intent);
            finish();
        } else if (popInclusive) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public boolean canPopPastActivity() {
        return true;
    }

    public void onBackPressed() {
        if (this.reactNavigationCoordinator.getDismissCloseBehavior(this)) {
            dismiss();
        } else {
            invokeDefaultOnBackPressed();
        }
    }

    public void invokeDefaultOnBackPressed() {
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        try {
            this.reactInstanceManager.onHostPause(this);
        } catch (AssertionError e) {
            Log.w(TAG, "Ignored AssertionError during onPause(). This Activity was probably killed in the background");
        }
        emitEvent(ON_DISAPPEAR, null);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.reactInstanceManager.onHostResume(this, this);
        emitEvent(ON_APPEAR, null);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.reactInstanceManager.onHostDestroy(this);
        this.reactNavigationCoordinator.unregisterComponent(this.instanceId);
        if (this.reactRootView != null) {
            this.reactRootView.unmountReactApplication();
        }
    }

    public void setMenuButtons(List<MenuButton> buttons) {
        this.menuButtons = buttons;
        supportInvalidateOptionsMenu();
    }

    public void setLink(String link2) {
        this.link = link2;
        supportInvalidateOptionsMenu();
    }

    public AirToolbar getToolbar() {
        return this.toolbar;
    }

    public ReactRootView getReactRootView() {
        return this.reactRootView;
    }

    public boolean isDismissible() {
        return this.reactNavigationCoordinator.getDismissCloseBehavior(this) || !(this instanceof ReactNativeModalActivity);
    }

    /* access modifiers changed from: protected */
    public Strap getOnTrimMemoryParams() {
        return Strap.make().mo11640kv("is_react_native", true).mo11639kv("module_name", getIntent() == null ? null : getIntent().getStringExtra(ReactNativeIntentUtils.REACT_MODULE_NAME));
    }

    /* access modifiers changed from: private */
    public void emitEvent(String eventName, Object object) {
        if (isSuccessfullyInitialized() && !supportIsDestroyed()) {
            ReactNativeUtils.maybeEmitEvent((ReactContext) this.reactInstanceManager.getCurrentReactContext(), String.format(Locale.ENGLISH, "AirbnbNavigatorScene.%s.%s", new Object[]{eventName, this.instanceId}), object);
        }
    }

    private void dismiss() {
        setResult(-1, new Intent().putExtra("isDismiss", isDismissible()));
        finish();
    }

    @TargetApi(21)
    protected static Transition makeSlideUpAnimation() {
        return makeSlideAnimation(80);
    }

    @TargetApi(21)
    public static Transition makeSlideLeftAnimation() {
        return makeSlideAnimation(5);
    }

    @TargetApi(21)
    private static Transition makeSlideAnimation(int gravity) {
        Slide slide = new Slide(gravity);
        slide.excludeTarget(16908335, true);
        slide.excludeTarget(16908336, true);
        slide.excludeTarget(C7663R.C7665id.toolbar, true);
        slide.setDuration(200);
        return slide;
    }

    public AirActivity getAirActivity() {
        return this;
    }

    /* access modifiers changed from: protected */
    public boolean isSuccessfullyInitialized() {
        return this.reactInstanceManager.isSuccessfullyInitialized();
    }

    @TargetApi(23)
    public void requestPermissions(String[] permissions2, int requestCode, PermissionListener listener) {
        this.permissionListener = listener;
        requestPermissions(permissions2, requestCode);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        if (this.permissionListener != null && this.permissionListener.onRequestPermissionsResult(requestCode, permissions2, grantResults)) {
            this.permissionListener = null;
        }
    }

    public void logout() {
        this.airbnbApi.clearUserSession();
        finishAffinity();
        startActivity(EntryActivityIntents.newIntent(this).addFlags(335544320));
    }
}
