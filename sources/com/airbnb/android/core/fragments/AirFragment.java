package com.airbnb.android.core.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p002v7.app.ActionBar;
import android.support.p002v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.AirFragmentFacade;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.ResourceManager;
import com.airbnb.android.core.ViewBreadcrumbManager;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.interfaces.LoaderFrameInterface;
import com.airbnb.android.core.requests.ObservableRequestManager;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ClientSessionValidator;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.MemoryUtils;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.NavigationUtils;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.utils.ActivityUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.erf.Erf;
import com.airbnb.p027n2.components.AirToolbar;
import com.squareup.otto.Bus;
import java.util.List;

public class AirFragment extends Fragment implements AirFragmentFacade {
    protected AirRequestInitializer airRequestInitializer;
    protected BusinessTravelAccountManager businessTravelAccountManager;
    protected ClientSessionValidator clientSessionValidator;
    private boolean customUpButtonEnabled;
    protected LoggingContextFactory loggingContextFactory;
    /* access modifiers changed from: protected */
    public AirbnbAccountManager mAccountManager;
    protected AirbnbApi mAirbnbApi;
    protected Bus mBus;
    protected CurrencyFormatter mCurrencyHelper;
    protected Erf mErf;
    protected MemoryUtils mMemoryUtils;
    protected AirbnbPreferences mPreferences;
    protected SharedPrefsHelper mPrefsHelper;
    protected NavigationLogging navigationAnalytics;
    /* access modifiers changed from: protected */
    public ObservableRequestManager requestManager;
    protected ResourceManager resourceManager;
    private AirToolbar toolbar;
    protected ViewBreadcrumbManager viewBreadcrumbManager;
    private Unbinder viewUnbinder;
    protected WishListManager wishListManager;

    public interface DoneClickListener {
        void onDoneClick();
    }

    public /* bridge */ /* synthetic */ Context getActivity() {
        return super.getActivity();
    }

    public void onCreate(Bundle savedInstanceState) {
        C0715L.m1189d(getLoggingTag(), "onCreate()");
        super.onCreate(savedInstanceState);
        if (setFlagSecure()) {
            getActivity().getWindow().setFlags(8192, 8192);
        }
        ((CoreGraph) CoreApplication.instance(getActivity()).component()).inject(this);
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        this.requestManager = ObservableRequestManager.onCreate(this.airRequestInitializer, this, savedInstanceState);
    }

    public void onSaveInstanceState(Bundle outState) {
        C0715L.m1189d(getLoggingTag(), "onSaveInstanceState()");
        super.onSaveInstanceState(outState);
        IcepickWrapper.saveInstanceState(this, outState);
        this.requestManager.onSaveInstanceState(outState);
    }

    public void onResume() {
        C0715L.m1189d(getLoggingTag(), "onResume()");
        AirbnbEventLogger.trackOnResume(getLoggingTag());
        super.onResume();
        this.requestManager.onResume();
        this.navigationAnalytics.trackImpression(this);
        this.clientSessionValidator.notifyAction();
        this.viewBreadcrumbManager.addBreadcrumb((Fragment) this);
    }

    public void setUserVisibleHint(boolean isVisibleToUser) {
        C0715L.m1189d(getLoggingTag(), "setUserVisibleHint: " + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
    }

    public void onPause() {
        C0715L.m1189d(getLoggingTag(), "onPause()");
        super.onPause();
        this.requestManager.onPause();
    }

    public void onStop() {
        super.onStop();
        if (this.customUpButtonEnabled) {
            disableCustomActionBarUpButton();
        }
    }

    public void onDestroyView() {
        C0715L.m1189d(getLoggingTag(), "onDestroyView()");
        getAirActivity().removeToolbar(this.toolbar, this);
        this.toolbar = null;
        if (this.viewUnbinder != null) {
            this.viewUnbinder.unbind();
            this.viewUnbinder = null;
        }
        super.onDestroyView();
    }

    public void onDestroy() {
        FragmentActivity activity = getActivity();
        C0715L.m1189d(getLoggingTag(), "onDestroy(). isFinishing=" + activity.isFinishing());
        this.requestManager.onDestroy((Fragment) this);
        super.onDestroy();
        if (setFlagSecure()) {
            activity.getWindow().clearFlags(8192);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (this.toolbar != null) {
            this.toolbar.onCreateOptionsMenu(menu, inflater);
        }
    }

    /* access modifiers changed from: protected */
    public void bindViews(View view) {
        Check.state(this.viewUnbinder == null, "Views were already bound");
        this.viewUnbinder = ButterKnife.bind(this, view);
    }

    /* access modifiers changed from: protected */
    public void setToolbar(AirToolbar toolbar2) {
        this.toolbar = toolbar2;
        getAirActivity().setToolbar(toolbar2, this);
    }

    public AirToolbar getToolbar() {
        return this.toolbar;
    }

    public void showLoader(boolean show) {
        if (getActivity() instanceof LoaderFrameInterface) {
            ((LoaderFrameInterface) getActivity()).showLoader(show);
        }
    }

    public void setLoaderFrameBackground(int color) {
        if (getActivity() instanceof LoaderFrameInterface) {
            ((LoaderFrameInterface) getActivity()).setLoaderFrameBackground(color);
        }
    }

    public ActionBar getActionBar() {
        return getAppCompatActivity().getSupportActionBar();
    }

    /* access modifiers changed from: protected */
    public void setActionBarTitle(int titleId) {
        getActionBar().setTitle(titleId);
    }

    /* access modifiers changed from: protected */
    public void setActionBarTitle(CharSequence title) {
        getActionBar().setTitle(title);
    }

    public AppCompatActivity getAppCompatActivity() {
        return (AppCompatActivity) getActivity();
    }

    public AirActivity getAirActivity() {
        return (AirActivity) getActivity();
    }

    /* access modifiers changed from: protected */
    public boolean setFlagSecure() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void enableCustomActionBarUpButton(int layoutId, int confirmButtonId, int labelResId, DoneClickListener listener) {
        this.customUpButtonEnabled = true;
        ActionBar actionbar = getActionBar();
        actionbar.setCustomView(layoutId);
        ((TextView) ButterKnife.findById(actionbar.getCustomView(), confirmButtonId)).setText(labelResId);
        actionbar.setDisplayHomeAsUpEnabled(false);
        actionbar.setDisplayShowCustomEnabled(true);
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayShowHomeEnabled(false);
        actionbar.getCustomView().setOnClickListener(AirFragment$$Lambda$1.lambdaFactory$(listener));
    }

    /* access modifiers changed from: protected */
    public void disableCustomActionBarUpButton() {
        ActionBar actionbar = getActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setDisplayShowCustomEnabled(false);
        actionbar.setDisplayShowTitleEnabled(true);
        actionbar.setDisplayShowHomeEnabled(true);
    }

    @SuppressLint({"RestrictedApi"})
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null) {
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void showFragment(Fragment fragment, int fragmentContainerId, FragmentTransitionType type, boolean addToBackStack) {
        NavigationUtils.showFragment(getChildFragmentManager(), getContext(), fragment, fragmentContainerId, type, addToBackStack);
    }

    public final void showFragment(Fragment fragment, int fragmentContainerId, FragmentTransitionType type, boolean addToBackStack, String tag) {
        NavigationUtils.showFragment(getChildFragmentManager(), getContext(), fragment, fragmentContainerId, type, addToBackStack, tag);
    }

    /* access modifiers changed from: protected */
    public final void showModal(Fragment fragment, int contentContainer, int modalContainer, boolean addToBackStack) {
        NavigationUtils.showModal(getChildFragmentManager(), getContext(), fragment, contentContainer, modalContainer, addToBackStack);
    }

    /* access modifiers changed from: protected */
    public final void showModal(Fragment fragment, int contentContainer, int modalContainer, boolean addToBackStack, String tag) {
        NavigationUtils.showModal(getChildFragmentManager(), getContext(), fragment, contentContainer, modalContainer, addToBackStack, tag);
    }

    public boolean isTabletScreen() {
        return MiscUtils.isTabletScreen(getActivity());
    }

    public boolean isLandscape() {
        return ActivityUtils.isLandscapeMode(getActivity());
    }

    /* access modifiers changed from: protected */
    public String getLoggingTag() {
        return getClass().getSimpleName();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Unknown;
    }

    public Strap getNavigationTrackingParams() {
        return Strap.make();
    }
}
