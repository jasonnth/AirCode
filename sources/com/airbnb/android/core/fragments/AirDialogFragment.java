package com.airbnb.android.core.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.p000v4.app.DialogFragment;
import android.support.p000v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.AirDialogFragmentFacade;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;

public class AirDialogFragment extends DialogFragment implements AirDialogFragmentFacade, NavigationLoggingElement {
    AirRequestInitializer airRequestInitializer;
    DebugSettings debugSettings;
    private boolean firstNavigationInstantiation;
    protected LoggingContextFactory loggingContextFactory;
    protected AirbnbAccountManager mAccountManager;
    NavigationLogging navigationAnalytics;
    protected RequestManager requestManager;
    private AirToolbar toolbar;
    private Unbinder viewUnbinder;

    public void onCreate(Bundle savedInstanceState) {
        C0715L.m1189d(getClass().getSimpleName(), "onCreate()");
        super.onCreate(savedInstanceState);
        this.firstNavigationInstantiation = savedInstanceState == null;
        ((CoreGraph) CoreApplication.instance(getActivity()).component()).inject(this);
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        this.requestManager = RequestManager.onCreate(this.airRequestInitializer, this, savedInstanceState);
        if (isLeafDialog()) {
            setStyle(0, C0716R.C0719style.Theme_TabNav_Leaf);
        }
    }

    public void onDestroy() {
        C0715L.m1189d(getClass().getSimpleName(), "onDestroy(). isFinishing=" + getActivity().isFinishing());
        super.onDestroy();
        this.requestManager.onDestroy((Fragment) this);
    }

    public void onDestroyView() {
        super.onDestroyView();
        getAirActivity().removeToolbar(this.toolbar, this);
        this.toolbar = null;
        if (this.viewUnbinder != null) {
            this.viewUnbinder.unbind();
            this.viewUnbinder = null;
        }
    }

    public void onResume() {
        String className = getClass().getSimpleName();
        C0715L.m1189d(className, "onResume()");
        AirbnbEventLogger.trackOnResume(className);
        super.onResume();
        this.requestManager.onResume();
        if (this.firstNavigationInstantiation) {
            this.firstNavigationInstantiation = false;
            this.navigationAnalytics.trackImpression(this);
        }
    }

    public void onStart() {
        super.onStart();
        if (getShowsDialog() && getDialog() != null && isLeafDialog()) {
            fixWindowSizeForLeaf();
        }
    }

    public void onPause() {
        C0715L.m1189d(getClass().getSimpleName(), "onPause()");
        super.onPause();
        this.requestManager.onPause();
    }

    public void onSaveInstanceState(Bundle outState) {
        C0715L.m1189d(getClass().getSimpleName(), "onSaveInstanceState()");
        super.onSaveInstanceState(outState);
        IcepickWrapper.saveInstanceState(this, outState);
        this.requestManager.onSaveInstanceState(outState);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (this.toolbar != null) {
            this.toolbar.onCreateOptionsMenu(menu, inflater);
        }
    }

    private void fixWindowSizeForLeaf() {
        getDialog().getWindow().setLayout(leafDialogWidth(), leafDialogHeight());
    }

    /* access modifiers changed from: protected */
    public int leafDialogWidth() {
        return getResources().getDimensionPixelSize(C0716R.dimen.n2_leaf_width);
    }

    /* access modifiers changed from: protected */
    public int leafDialogHeight() {
        return getResources().getDimensionPixelSize(C0716R.dimen.n2_leaf_height);
    }

    public boolean shouldShowAsDialog(Context context) {
        return MiscUtils.isTabletScreen(context);
    }

    public boolean isLeafDialog() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void setToolbar(AirToolbar toolbar2) {
        this.toolbar = toolbar2;
        getAirActivity().setToolbar(toolbar2, this);
    }

    /* access modifiers changed from: protected */
    public void bindViews(View view) {
        Check.state(this.viewUnbinder == null, "Views were already bound");
        this.viewUnbinder = ButterKnife.bind(this, view);
    }

    public AirActivity getAirActivity() {
        return (AirActivity) getActivity();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Unknown;
    }

    public Strap getNavigationTrackingParams() {
        return Strap.make();
    }
}
