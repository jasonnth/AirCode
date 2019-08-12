package com.airbnb.android.core.activities;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.NavUtils;
import android.support.p000v4.app.TaskStackBuilder;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.support.p002v7.app.ActionBar;
import android.support.p002v7.app.AppCompatActivity;
import android.support.p002v7.widget.Toolbar;
import android.support.p002v7.widget.VectorEnabledTintResources;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewPropertyAnimator;
import android.view.ViewStub;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.AirActivityFacade;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.ResourceManager;
import com.airbnb.android.core.ViewBreadcrumbManager;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.AppLaunchAnalytics;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.dls.DLSJitneyLogger;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.intents.EntryActivityIntents;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.interfaces.OnHomeListener;
import com.airbnb.android.core.react.AirReactInstanceManager;
import com.airbnb.android.core.superhero.SuperHeroInterface;
import com.airbnb.android.core.superhero.SuperHeroInterfaceState;
import com.airbnb.android.core.superhero.SuperHeroManager;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ColdStartExperimentDeliverer;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.NavigationUtils;
import com.airbnb.android.core.utils.PushNotificationUtil;
import com.airbnb.android.core.utils.ShakeFeedbackSensorListener;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.views.SuperHeroDismissView;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.ConcurrentUtil;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.animation.SimpleAnimatorListener;
import com.airbnb.erf.Erf;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.browser.DLSOverlayLayoutInflaterFactory;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.internal.ComponentManager;
import com.airbnb.p027n2.primitives.fonts.FontManager;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import com.squareup.otto.Bus;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public abstract class AirActivity extends AppCompatActivity implements AirActivityFacade {
    private static final int SUPER_HERO_INITIAL_DURATION = 600;
    private static final String TAG = AirActivity.class.getSimpleName();
    DLSJitneyLogger DLSlogger;
    protected AirbnbAccountManager accountManager;
    private final List<ToolbarFragmentPair> addedToolbars = new ArrayList();
    AirRequestInitializer airRequestInitializer;
    protected AirbnbApi airbnbApi;
    protected Bus bus;
    Lazy<ComponentManager> componentManager;
    /* access modifiers changed from: private */
    public View content;
    protected CurrencyFormatter currencyFormatter;
    protected Erf erf;
    private boolean floatingActivity;
    private final FontManager fontManager = new FontManager();
    /* access modifiers changed from: private */
    public FrameLayout heroContainer;
    private boolean isResumed;
    protected AppLaunchAnalytics launchAnalytics;
    protected NavigationLogging navigationAnalytics;
    private OnBackListener onBackPressedListener;
    private OnHomeListener onHomeActionPressedListener;
    protected AirbnbPreferences preferences;
    protected Lazy<AirReactInstanceManager> reactInstanceManager;
    protected RequestManager requestManager;
    protected ResourceManager resourceManager;
    ShakeFeedbackSensorListener shakeHelper;
    protected SharedPrefsHelper sharedPrefsHelper;
    /* access modifiers changed from: private */
    public SuperHeroDismissView superHeroDismissView;
    private final SuperHeroInterface superHeroInterface = new SuperHeroInterface() {
        private final SimpleAnimatorListener closeListener = new SimpleAnimatorListener() {
            public void onAnimationEnd(Animator animation) {
                C58042.this.close();
            }

            public void onAnimationCancel(Animator animation) {
                C58042.this.close();
            }
        };
        final Interpolator interpolator = new FastOutSlowInInterpolator();
        private SuperHeroInterfaceState state = SuperHeroInterfaceState.CLOSED;

        public void show(Fragment fragment) {
            if (((AirReactInstanceManager) AirActivity.this.reactInstanceManager.get()).isSuccessfullyInitialized()) {
                showIfReactIsInitialized(fragment);
            } else {
                ((AirReactInstanceManager) AirActivity.this.reactInstanceManager.get()).addReactInstanceEventListener(AirActivity$2$$Lambda$1.lambdaFactory$(this, fragment));
            }
        }

        /* access modifiers changed from: private */
        @SuppressLint({"NewApi"})
        public void showIfReactIsInitialized(Fragment fragment) {
            if (!AirActivity.this.supportIsDestroyed()) {
                if (!((AirReactInstanceManager) AirActivity.this.reactInstanceManager.get()).isSuccessfullyInitialized()) {
                    BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Tried to show SuperHero but React was not initialized"));
                    return;
                }
                this.state = SuperHeroInterfaceState.LOADING;
                AirActivity.this.showFragment(fragment, C0716R.C0718id.hero_container, FragmentTransitionType.FadeInAndOut, false, SuperHeroManager.SUPER_HERO_FRAGMENT_TAG);
                AirActivity.this.heroContainer.setVisibility(0);
                animateInitial(AirActivity.this.content);
                animateInitial(AirActivity.this.superHeroDismissView).alpha(1.0f);
                AirActivity.this.superHeroDismissView.enable(AirActivity$2$$Lambda$2.lambdaFactory$(this));
            }
        }

        public void presentFull() {
            if (!AirActivity.this.supportIsDestroyed() && AirActivity.this.isActivityResumed()) {
                this.state = SuperHeroInterfaceState.EXPANDED;
                Check.notNull(AirActivity.this.getSupportFragmentManager().findFragmentByTag(SuperHeroManager.SUPER_HERO_FRAGMENT_TAG));
                animateFull(AirActivity.this.content);
                animateFull(AirActivity.this.superHeroDismissView);
            }
        }

        public void dismiss() {
            if (!AirActivity.this.supportIsDestroyed() && AirActivity.this.isActivityResumed()) {
                this.state = SuperHeroInterfaceState.CLOSED;
                animateClose(AirActivity.this.content).setListener(this.closeListener);
                animateClose(AirActivity.this.superHeroDismissView).alpha(0.0f);
            }
        }

        public SuperHeroInterfaceState getState() {
            return this.state;
        }

        private ViewPropertyAnimator animateInitial(View view) {
            return view.animate().setDuration(600).translationY(AirActivity.this.getResources().getDimension(C0716R.dimen.hero_collapsed_height));
        }

        private ViewPropertyAnimator animateFull(View view) {
            return view.animate().translationY((float) AirActivity.this.heroContainer.getHeight()).setInterpolator(this.interpolator);
        }

        private ViewPropertyAnimator animateClose(View view) {
            return view.animate().translationY(0.0f).setInterpolator(this.interpolator);
        }

        /* access modifiers changed from: private */
        public void close() {
            if (!AirActivity.this.supportIsDestroyed() && AirActivity.this.isActivityResumed()) {
                AirActivity.this.content.animate().setListener(null);
                FragmentManager fm = AirActivity.this.getSupportFragmentManager();
                Fragment heroFragment = fm.findFragmentByTag(SuperHeroManager.SUPER_HERO_FRAGMENT_TAG);
                if (heroFragment != null) {
                    fm.beginTransaction().remove(heroFragment).commit();
                }
                AirActivity.this.heroContainer.setVisibility(8);
                AirActivity.this.superHeroDismissView.disable();
            }
        }
    };
    protected SuperHeroManager superHeroManager;
    protected ViewBreadcrumbManager viewBreadcrumbManager;
    protected WishListManager wishListManager;

    private static class ToolbarFragmentPair {
        final Fragment fragment;
        final AirToolbar toolbar;

        public ToolbarFragmentPair(AirToolbar toolbar2, Fragment fragment2) {
            this.toolbar = toolbar2;
            this.fragment = fragment2;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ToolbarFragmentPair pair = (ToolbarFragmentPair) o;
            if (this.toolbar == null ? pair.toolbar != null : !this.toolbar.equals(pair.toolbar)) {
                return false;
            }
            if (this.fragment != null) {
                return this.fragment.equals(pair.fragment);
            }
            if (pair.fragment != null) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int result;
            int i = 0;
            if (this.toolbar != null) {
                result = this.toolbar.hashCode();
            } else {
                result = 0;
            }
            int i2 = result * 31;
            if (this.fragment != null) {
                i = this.fragment.hashCode();
            }
            return i2 + i;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        C0715L.m1189d(getClass().getSimpleName(), "onCreate()");
        ((CoreGraph) CoreApplication.instance(this).component()).inject(this);
        if (!ColdStartExperimentDeliverer.isInTreatmentGroup()) {
            this.reactInstanceManager.get();
        }
        if (DebugSettings.DLS_COMPONENT_OVERLAYS.isEnabled()) {
            DLSOverlayLayoutInflaterFactory.setup(this);
        }
        super.onCreate(savedInstanceState);
        this.componentManager = ((CoreGraph) CoreApplication.instance(this).component()).componentManager();
        if (setFlagSecure()) {
            getWindow().setFlags(8192, 8192);
        }
        if (!isTabletScreen() && !BuildHelper.isDevelopmentBuild() && shouldLockToPortrait() && !MiscUtils.isUserAMonkey()) {
            setRequestedOrientation(1);
        }
        BugsnagWrapper.setContext(getClass().getSimpleName());
        this.floatingActivity = MiscUtils.getBooleanFromAttribute(this, 16842839);
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        this.requestManager = RequestManager.onCreate(this.airRequestInitializer, this, savedInstanceState);
        if (!isFloatingActivity() && !hasCustomEnterTransition()) {
            overridePendingTransition(C0716R.anim.activity_transition_slide_in_new, C0716R.anim.activity_transition_fade_out_prev);
        }
        if (savedInstanceState == null) {
            handleNewIntent(getIntent());
        }
        if (Experiments.trackVisualComponentDisplay()) {
            ((ComponentManager) this.componentManager.get()).onCreate(this, AirActivity$$Lambda$1.lambdaFactory$(this));
        }
    }

    @SuppressLint({"RestrictedAPI"})
    public Resources getResources() {
        if (!Experiments.useDynamicStrings()) {
            return super.getResources();
        }
        if (VectorEnabledTintResources.shouldBeUsed()) {
            return super.getResources();
        }
        return (Resources) CoreApplication.instance(this).component().dynamicStringsResources().get();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleNewIntent(intent);
    }

    private void handleNewIntent(Intent intent) {
        try {
            PushNotificationUtil.ackPushNotificationOpened(intent);
        } catch (Exception e) {
            Log.e(TAG, "Failed to parse incoming intent", e);
        }
    }

    public void setContentView(int layoutResID) {
        super.setContentView(C0716R.layout.activity_air);
        this.heroContainer = (FrameLayout) findViewById(C0716R.C0718id.hero_container);
        this.superHeroDismissView = (SuperHeroDismissView) findViewById(C0716R.C0718id.super_hero_touch_view);
        ViewStub contentStub = (ViewStub) findViewById(C0716R.C0718id.content_stub);
        contentStub.setLayoutResource(layoutResID);
        this.content = contentStub.inflate();
    }

    public void setContentView(View view) {
        super.setContentView(view);
        throw new IllegalStateException("Tried to call setContentView(View view) which doesn't super SuperHero. Please add support for SuperHero.");
    }

    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        throw new IllegalStateException("Tried to call setContentView(View view, LayoutParams params) which doesn't super SuperHero. Please add support for SuperHero.");
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        C0715L.m1189d(getClass().getSimpleName(), "onSaveInstanceState()");
        super.onSaveInstanceState(outState);
        IcepickWrapper.saveInstanceState(this, outState);
        this.requestManager.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean setFlagSecure() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && shouldUseXAsUpButton()) {
            actionBar.setHomeAsUpIndicator(ColorizedDrawable.forIdWithColor(this, C0716R.C0717drawable.ic_action_close, C0716R.color.c_hof));
        }
        validateUserLoggedIn();
    }

    public void scheduleStartPostponedTransition() {
        getWindow().getDecorView().getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            public boolean onPreDraw() {
                AirActivity.this.getWindow().getDecorView().getViewTreeObserver().removeOnPreDrawListener(this);
                AirActivity.this.supportStartPostponedEnterTransition();
                return true;
            }
        });
    }

    private void validateUserLoggedIn() {
        if (!this.accountManager.hasAccessToken() && !denyRequireAccountFromChild()) {
            BugsnagWrapper.notify((Throwable) new IllegalStateException(String.format("[EntryActivity Error] %s launched in logged-out state with Intent %s.", new Object[]{getClass().getCanonicalName(), getIntent().toString()})));
            startActivity(EntryActivityIntents.newIntent(this, getIntent()));
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public boolean denyRequireAccountFromChild() {
        return getCallingActivity() != null && getCallingActivity().getClassName().equals(Activities.login().getName());
    }

    /* access modifiers changed from: protected */
    public boolean shouldUseXAsUpButton() {
        return isFloatingActivity();
    }

    /* access modifiers changed from: protected */
    public boolean shouldLockToPortrait() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        C0715L.m1189d(getClass().getSimpleName(), "onDestroy(). isFinishing=" + isFinishing());
        this.requestManager.onDestroy((Activity) this);
        if (Experiments.trackVisualComponentDisplay()) {
            ((ComponentManager) this.componentManager.get()).onDestroy(this);
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        String className = getClass().getSimpleName();
        C0715L.m1189d(className, "onResume()");
        AirbnbEventLogger.trackOnResume(className);
        super.onResume();
        this.superHeroManager.setInterface(this.superHeroInterface);
        this.viewBreadcrumbManager.addBreadcrumb((AppCompatActivity) this);
        long firstActivityLoadedTimeMs = System.currentTimeMillis();
        if (ColdStartExperimentDeliverer.isInTreatmentGroup()) {
            ConcurrentUtil.deferParallel(AirActivity$$Lambda$2.lambdaFactory$(this, firstActivityLoadedTimeMs));
        } else {
            this.launchAnalytics.trackFirstActivityLoaded(getClass().getSimpleName(), firstActivityLoadedTimeMs);
        }
        if (BuildHelper.isDevelopmentBuild()) {
            ((AirReactInstanceManager) this.reactInstanceManager.get()).setDevSupportEnabled(true);
        }
    }

    /* access modifiers changed from: protected */
    public void onResumeFragments() {
        super.onResumeFragments();
        this.isResumed = true;
        this.requestManager.onResume();
        this.shakeHelper.onResume(this);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        C0715L.m1189d(getClass().getSimpleName(), "onPause()");
        super.onPause();
        this.isResumed = false;
        this.shakeHelper.onPause();
        this.requestManager.onPause();
        this.superHeroManager.removeInterfaceIfSet(this.superHeroInterface);
        if (BuildHelper.isDevelopmentBuild()) {
            ((AirReactInstanceManager) this.reactInstanceManager.get()).setDevSupportEnabled(false);
        }
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            long firstActivityShownTimeMs = System.currentTimeMillis();
            if (ColdStartExperimentDeliverer.isInTreatmentGroup()) {
                ConcurrentUtil.defer(AirActivity$$Lambda$3.lambdaFactory$(this, firstActivityShownTimeMs));
            } else {
                this.launchAnalytics.trackFirstActivityShown(getClass().getSimpleName(), firstActivityShownTimeMs);
            }
            ColdStartExperimentDeliverer.deliverExperimentIfNeeded();
        }
    }

    public void setOnHomePressedListener(OnHomeListener listener) {
        this.onHomeActionPressedListener = listener;
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        if (MiscUtils.isUserAMonkey() && System.currentTimeMillis() % 10 != 0) {
            return;
        }
        if (this.onHomeActionPressedListener != null && this.onHomeActionPressedListener.onHomePressed()) {
            return;
        }
        if (!homeActionPopsFragmentStack() || getSupportFragmentManager().getBackStackEntryCount() <= 0) {
            navigateUp();
        } else {
            onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return false;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        onHomeActionPressed();
        return true;
    }

    /* access modifiers changed from: protected */
    public void navigateUp() {
        boolean shouldUpRecreateTask;
        if (TextUtils.isEmpty(NavUtils.getParentActivityName(this))) {
            supportFinishAfterTransition();
            return;
        }
        Intent upIntent = NavUtils.getParentActivityIntent(this);
        try {
            shouldUpRecreateTask = NavUtils.shouldUpRecreateTask(this, upIntent);
        } catch (NullPointerException e) {
            shouldUpRecreateTask = false;
        }
        if (shouldUpRecreateTask || (getCallingActivity() != null && Activities.webIntentDispatch().getName().equals(getCallingActivity().getClassName()))) {
            TaskStackBuilder.create(this).addNextIntentWithParentStack(upIntent).startActivities();
            if (!this.floatingActivity && !hasCustomEnterTransition()) {
                overridePendingTransition(C0716R.anim.activity_transition_back_to_prev, C0716R.anim.activity_transition_slide_out_new);
                return;
            }
            return;
        }
        supportFinishAfterTransition();
    }

    public void finish() {
        super.finish();
        if (!this.floatingActivity && !hasCustomEnterTransition()) {
            overridePendingTransition(C0716R.anim.activity_transition_back_to_prev, C0716R.anim.activity_transition_slide_out_new);
        }
    }

    public boolean isActivityResumed() {
        return this.isResumed;
    }

    @TargetApi(16)
    public boolean navigateUpTo(Intent upIntent) {
        boolean navigateUpTo = super.navigateUpTo(upIntent);
        if (!this.floatingActivity) {
            overridePendingTransition(C0716R.anim.activity_transition_back_to_prev, C0716R.anim.activity_transition_slide_out_new);
        }
        return navigateUpTo;
    }

    public void removeToolbar(AirToolbar toolbar, Fragment fragment) {
        if (!this.addedToolbars.isEmpty()) {
            this.addedToolbars.remove(new ToolbarFragmentPair(toolbar, fragment));
            setActiveToolbar();
        }
    }

    public void setToolbar(AirToolbar toolbar) {
        setToolbar(toolbar, null);
    }

    public void setToolbar(AirToolbar toolbar, Fragment fragment) {
        ToolbarFragmentPair pair = new ToolbarFragmentPair(toolbar, fragment);
        this.addedToolbars.remove(pair);
        this.addedToolbars.add(pair);
        setActiveToolbar();
    }

    private void setActiveToolbar() {
        if (!this.addedToolbars.isEmpty()) {
            ListIterator<ToolbarFragmentPair> iterator = this.addedToolbars.listIterator(this.addedToolbars.size());
            ToolbarFragmentPair pair = (ToolbarFragmentPair) iterator.previous();
            while (iterator.hasPrevious()) {
                ToolbarFragmentPair tempPair = (ToolbarFragmentPair) iterator.previous();
                if ((tempPair.fragment instanceof AirDialogFragment) && ((AirDialogFragment) tempPair.fragment).shouldShowAsDialog(this)) {
                    setSupportActionBar(tempPair.toolbar);
                    return;
                }
            }
            setSupportActionBar(pair.toolbar);
        }
    }

    @Deprecated
    public void setSupportActionBar(Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
    }

    @Deprecated
    public ActionBar setupActionBar(String title, String subtitle) {
        ActionBar actionBar = enableActionBarHomeAsUpAndShowTitle();
        actionBar.setTitle(this.fontManager.wrapActionbarSpan(title, this));
        if (subtitle != null) {
            actionBar.setSubtitle(this.fontManager.wrapActionbarSpan(subtitle, this));
        }
        return actionBar;
    }

    @Deprecated
    public ActionBar setupActionBar(String title) {
        return setupActionBar(title, (String) null);
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public void setupTransparentActionBar() {
        setupActionBar(0, new Object[0]);
    }

    @Deprecated
    public ActionBar setupActionBar(int title, Object... args) {
        ActionBar actionBar = enableActionBarHomeAsUpAndShowTitle();
        if (title > 0) {
            if (actionBar != null) {
                if (args == null || args.length <= 0) {
                    actionBar.setTitle(this.fontManager.wrapActionbarSpan(getString(title), this));
                } else {
                    actionBar.setTitle(this.fontManager.wrapActionbarSpan(getString(title, args), this));
                }
            } else if (args == null || args.length <= 0) {
                setTitle(this.fontManager.wrapActionbarSpan(getString(title), this));
            } else {
                setTitle(this.fontManager.wrapActionbarSpan(getString(title, args), this));
            }
        }
        return actionBar;
    }

    @Deprecated
    public ActionBar setupActionBar(CharSequence title) {
        ActionBar actionBar = enableActionBarHomeAsUpAndShowTitle();
        actionBar.setTitle(title);
        return actionBar;
    }

    @Deprecated
    private ActionBar enableActionBarHomeAsUpAndShowTitle() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        } else {
            try {
                Resources resources = getResources();
                getWindow().getDecorView().findViewById(resources.getIdentifier("titleDivider", "id", "android")).setBackgroundColor(resources.getColor(C0716R.color.c_rausch));
            } catch (Exception e) {
            }
        }
        return actionBar;
    }

    public void dispatchActivityResult(int requestCode, int resultCode, Intent data) {
        onActivityResult(requestCode, resultCode, data);
    }

    public boolean isFloatingActivity() {
        return this.floatingActivity;
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"RestrictedApi"})
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible()) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* access modifiers changed from: protected */
    public boolean isBackStackEmpty() {
        return getSupportFragmentManager().getBackStackEntryCount() == 0;
    }

    /* access modifiers changed from: protected */
    public final void showFragment(Fragment fragment, int fragmentContainerId, FragmentTransitionType type, boolean addToBackStack) {
        NavigationUtils.showFragment(getSupportFragmentManager(), this, fragment, fragmentContainerId, type, addToBackStack);
    }

    public final void showFragment(Fragment fragment, int fragmentContainerId, FragmentTransitionType type, boolean addToBackStack, String tag) {
        NavigationUtils.showFragment(getSupportFragmentManager(), this, fragment, fragmentContainerId, type, addToBackStack, tag);
    }

    /* access modifiers changed from: protected */
    public final void showModal(Fragment fragment, int contentContainer, int modalContainer, boolean addToBackStack) {
        NavigationUtils.showModal(getSupportFragmentManager(), this, fragment, contentContainer, modalContainer, addToBackStack);
    }

    public final void showModal(Fragment fragment, int contentContainer, int modalContainer, boolean addToBackStack, String tag) {
        NavigationUtils.showModal(getSupportFragmentManager(), this, fragment, contentContainer, modalContainer, addToBackStack, tag);
    }

    /* access modifiers changed from: protected */
    public boolean isTabletScreen() {
        return MiscUtils.isTabletScreen(this);
    }

    public void setOnBackPressedListener(OnBackListener listener) {
        this.onBackPressedListener = listener;
    }

    /* access modifiers changed from: protected */
    @TargetApi(17)
    public boolean supportIsDestroyed() {
        return AndroidVersion.isAtLeastJellyBeanMR1() && isDestroyed();
    }

    public void onBackPressed() {
        if (this.onBackPressedListener == null || !this.onBackPressedListener.onBackPressed()) {
            super.onBackPressed();
        }
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (!BuildHelper.isDevelopmentBuild() || keyCode != 82) {
            return super.onKeyUp(keyCode, event);
        }
        ((AirReactInstanceManager) this.reactInstanceManager.get()).showDevOptionsDialog();
        return true;
    }

    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        ActivityManager actManager = (ActivityManager) getSystemService("activity");
        MemoryInfo memInfo = new MemoryInfo();
        actManager.getMemoryInfo(memInfo);
        AirbnbEventLogger.track("android_eng", Strap.make().mo11637kv("level", level).mo11638kv("total_memory", memInfo.totalMem).mo11638kv("available_memory", memInfo.availMem).mo11638kv("memory_threshold", memInfo.threshold).mo11640kv("is_low_memory", memInfo.lowMemory).mix(getOnTrimMemoryParams()));
    }

    /* access modifiers changed from: protected */
    public Strap getOnTrimMemoryParams() {
        return null;
    }

    public SuperHeroManager getSuperHeroManager() {
        return this.superHeroManager;
    }
}
