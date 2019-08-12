package com.airbnb.android.react;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.art.ARTRenderableViewManager;
import com.facebook.react.views.art.ARTSurfaceViewManager;
import com.facebook.react.views.drawer.ReactDrawerLayoutManager;
import com.facebook.react.views.modal.ReactModalHostManager;
import com.facebook.react.views.picker.ReactDialogPickerManager;
import com.facebook.react.views.picker.ReactDropdownPickerManager;
import com.facebook.react.views.progressbar.ReactProgressBarViewManager;
import com.facebook.react.views.recyclerview.RecyclerViewBackedScrollViewManager;
import com.facebook.react.views.scroll.ReactHorizontalScrollViewManager;
import com.facebook.react.views.scroll.ReactScrollViewManager;
import com.facebook.react.views.slider.ReactSliderManager;
import com.facebook.react.views.swiperefresh.SwipeRefreshLayoutManager;
import com.facebook.react.views.switchview.ReactSwitchManager;
import com.facebook.react.views.text.ReactRawTextManager;
import com.facebook.react.views.text.ReactTextViewManager;
import com.facebook.react.views.text.ReactVirtualTextViewManager;
import com.facebook.react.views.textinput.ReactTextInputManager;
import com.facebook.react.views.toolbar.ReactToolbarManager;
import com.facebook.react.views.view.ReactViewManager;
import com.facebook.react.views.viewpager.ReactViewPagerManager;
import com.facebook.react.views.webview.ReactWebViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AirbnbReactPackage implements ReactPackage {
    private final ReactNavigationCoordinator coordinator;
    private final ReactNativeModulesProvider reactNativeModulesProvider;

    public AirbnbReactPackage(ReactNativeModulesProvider reactNativeModulesProvider2, ReactNavigationCoordinator coordinator2) {
        this.reactNativeModulesProvider = reactNativeModulesProvider2;
        this.coordinator = coordinator2;
    }

    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        return this.reactNativeModulesProvider.get(reactContext);
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.asList(new ViewManager[]{ARTRenderableViewManager.createARTGroupViewManager(), ARTRenderableViewManager.createARTShapeViewManager(), ARTRenderableViewManager.createARTTextViewManager(), new ARTSurfaceViewManager(), new ReactDialogPickerManager(), new ReactDrawerLayoutManager(), new ReactDropdownPickerManager(), new ReactHorizontalScrollViewManager(), new ReactModalHostManager(), new ReactProgressBarViewManager(), new ReactRawTextManager(), new ReactScrollViewManager(), new ReactSliderManager(), new ReactSwitchManager(), new ReactTextInputManager(), new ReactTextViewManager(), new ReactToolbarManager(), new ReactViewManager(), new ReactViewPagerManager(), new ReactVirtualTextViewManager(), new ReactWebViewManager(), new RecyclerViewBackedScrollViewManager(), new SwipeRefreshLayoutManager(), new AirbnbReactScrollViewManager(this.coordinator), new JellyfishViewManager(), new ToolbarPusherManager(), new ReactAirImageManager(), new LottieAnimationViewManager(), new NoOpTextInlineImageViewManager(), new SharedElementViewManager(this.coordinator), new SharedElementGroupManager()});
    }
}
