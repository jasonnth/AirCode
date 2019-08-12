package com.airbnb.android.react;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import butterknife.ButterKnife;
import com.airbnb.android.core.AirActivityFacade;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.react.AirReactInstanceManager;
import com.airbnb.android.utils.ReactNativeIntentUtils;
import com.airbnb.jitney.event.logging.NativeMeasurementType.p159v1.C2445NativeMeasurementType;
import com.airbnb.p027n2.components.AirToolbar;
import com.facebook.react.ReactRootView;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.google.common.collect.ImmutableMap.Builder;
import java.util.List;
import java.util.Locale;

public class ReactNativeFragment extends AirFragment implements ReactInterface, DefaultHardwareBackBtnHandler {
    private static final String AIRBNB_INSTANCE_ID_PROP = "airbnbInstanceId";
    private static final String ON_APPEAR = "onAppear";
    private static final String ON_BUTTON_PRESS = "onButtonPress";
    private static final String ON_DISAPPEAR = "onDisappear";
    private static final String ON_LINK_PRESS = "onLinkPress";
    private static int UUID = 1;
    private ReactInterfaceManager activityManager;
    private String instanceId;
    private String link;
    private List<MenuButton> menuButtons;
    private String moduleName;
    PerformanceLogger performanceLogger;
    AirReactInstanceManager reactInstanceManager;
    ReactNavigationCoordinator reactNavigationCoordinator;
    private ReactRootView reactRootView;

    public /* bridge */ /* synthetic */ AirActivityFacade getAirActivity() {
        return super.getAirActivity();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((ReactGraph) CoreApplication.instance(getContext()).component()).inject(this);
        initReactNative();
        this.moduleName = getArguments().getString(ReactNativeIntentUtils.REACT_MODULE_NAME);
        this.performanceLogger.markStart(this.moduleName, new Builder().put("react_native", Boolean.toString(true)).build(), null);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C7663R.layout.fragment_react_native, container, false);
        initReactNative();
        return v;
    }

    private void initReactNative() {
        if (this.reactRootView == null && getView() != null) {
            if (!isSuccessfullyInitialized()) {
                this.reactInstanceManager.addReactInstanceEventListener(ReactNativeFragment$$Lambda$1.lambdaFactory$(this));
            } else {
                onCreateWithReactContext();
            }
            this.activityManager = new ReactInterfaceManager(this);
            this.reactNavigationCoordinator.registerComponent(this, this.instanceId);
        }
    }

    /* access modifiers changed from: private */
    public void onCreateWithReactContext() {
        if (getView() != null) {
            ButterKnife.findById(getView(), C7663R.C7665id.loading_view).setVisibility(8);
            if (!isSuccessfullyInitialized()) {
                ReactNativeUtils.showAlertBecauseChecksFailed(getContext(), null);
                return;
            }
            String moduleName2 = getArguments().getString(ReactNativeIntentUtils.REACT_MODULE_NAME);
            int i = UUID;
            UUID = i + 1;
            this.instanceId = String.format(Locale.ENGLISH, "%1s_fragment_%2$d", new Object[]{moduleName2, Integer.valueOf(i)});
            Bundle props = getArguments().getBundle(ReactNativeIntentUtils.REACT_PROPS);
            if (props == null) {
                props = new Bundle();
            }
            props.putString(AIRBNB_INSTANCE_ID_PROP, this.instanceId);
            if (this.reactRootView == null) {
                ViewStub reactViewStub = (ViewStub) ButterKnife.findById(getView(), C7663R.C7665id.react_root_view_stub);
                reactViewStub.setLayoutResource(C7663R.layout.view_holder_react_root_view);
                this.reactRootView = (ReactRootView) reactViewStub.inflate();
            }
            this.reactInstanceManager.startReactApplication(this.reactRootView, moduleName2, props);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.activityManager.onActivityResult(requestCode, resultCode, data);
    }

    public void invokeDefaultOnBackPressed() {
        getActivity().onBackPressed();
    }

    public void onPause() {
        super.onPause();
        this.reactInstanceManager.onHostPause(getActivity());
        emitEvent(ON_DISAPPEAR, null);
    }

    public void onResume() {
        super.onResume();
        this.reactInstanceManager.onHostResume(getActivity(), this);
        emitEvent(ON_APPEAR, null);
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.reactNavigationCoordinator.unregisterComponent(this.instanceId);
        if (this.reactRootView != null) {
            this.reactRootView.unmountReactApplication();
        }
    }

    public boolean isDismissible() {
        return this.reactNavigationCoordinator.getDismissCloseBehavior(this);
    }

    public String getInstanceId() {
        return this.instanceId;
    }

    public ReactRootView getReactRootView() {
        return this.reactRootView;
    }

    public void signalFirstRenderComplete() {
        this.performanceLogger.markCompleted(this.moduleName, C2445NativeMeasurementType.RenderDuration, this.moduleName);
    }

    public void setMenuButtons(List<MenuButton> buttons) {
        this.menuButtons = buttons;
        getActivity().supportInvalidateOptionsMenu();
    }

    public void setLink(String link2) {
        this.link = link2;
        getActivity().supportInvalidateOptionsMenu();
    }

    public void notifySharedElementAddition() {
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        AirToolbar toolbar = getToolbar();
        if (toolbar != null) {
            toolbar.onCreateOptionsMenu(0, menu, inflater);
            createOptionsMenu(menu);
        }
    }

    private void createOptionsMenu(Menu menu) {
        if (this.link != null) {
            menu.add(this.link).setShowAsAction(1);
        } else if (this.menuButtons != null) {
            NavigatorModule.addButtonsToMenu(getContext(), menu, this.menuButtons, ReactNativeFragment$$Lambda$2.lambdaFactory$(this));
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        emitEvent(ON_LINK_PRESS, null);
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean isSuccessfullyInitialized() {
        return this.reactInstanceManager.isSuccessfullyInitialized();
    }

    /* access modifiers changed from: private */
    public void emitEvent(String eventName, Object object) {
        if (isSuccessfullyInitialized()) {
            ReactNativeUtils.maybeEmitEvent((ReactContext) this.reactInstanceManager.getCurrentReactContext(), String.format(Locale.ENGLISH, "AirbnbNavigatorScene.%s.%s", new Object[]{eventName, this.instanceId}), object);
        }
    }
}
